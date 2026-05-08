package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.br.justcomposelabs.R

/*
    rememberUpdatedState: reference a value in an effect that shouldn't restart if the value changes
 */


class AppViewModel : ViewModel() {

}


@Preview(showBackground = true)
@Composable
fun MainApp(
    viewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    /*
        https://share.google/aimode/4viYLPaBSCVtGwJIf
        - navController.currentBackStackEntryAsState()
            - Estado observavel global do controle de navegacao. Serve para UI reagir a mudancas
            de tela, para bottomNavigation ser selecionada e se a TopBar mostra o botão de voltar

     */
    val backStackEntry by navController.currentBackStackEntryAsState()

    val titleCurrentScreen = stringResource(
        backStackEntry?.toRoute<NavRoute>()?.title ?: R.string.splashscreen
    )

    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = titleCurrentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { paddingValues ->
        // NavHost é um Container de layout: https://share.google/aimode/a5qVGB8aklEL4zCzn
        NavHost(
            navController = navController,
            startDestination = NavRoute.SplashScreenRoute,
            modifier = Modifier
                .fillMaxSize()
                /*
                    Passando os valores de padding do Scaffold para o NavHost para evitar
                    que o conteúdo exibido por NavHost seja sobreposto por uma TopBar, ou
                    BottomBar

                    Como qualquer outro container, NavHost pode precisar de:
                        - Background, Acessbilidade
                        - Clipping: Para arredondar bordas de toda a área de navegacao
                 */
                .padding(paddingValues),
            /*
                Animacoes globais para todas as telas
             */
        ) {
            composable<NavRoute.SplashScreenRoute> {
                SplashScreenComponent {
                    navController.navigate(NavRoute.HomeScreenRoute)
                }
            }


            composable<NavRoute.HomeScreenRoute>(
                enterTransition = { slideInHorizontally { it } },
                exitTransition = { slideOutHorizontally { -it } }
            ) {
                HomeScreen { profile ->
                    navController.navigate(NavRoute.ProfileScreenRoute(profile))
                }
            }

            composable<NavRoute.ProfileScreenRoute> { scopedBackStackEntry ->
                /*
                    Qual a diferença de usar

                    val backStackEntry by navController.currentBackStackEntryAsState() e o
                    parâmetro it/backStackEntry/scopedBackStackEntry da função composable?

                    - O parâmetro da função composable é o dado local da tela/componente
                    específico. Usado para extrair os argumentos passados para
                    a tela.

                    - esse parâmetro também representa a instância que foi passada para tela e
                    está na pilha, é possível ter mais de uma instância da mesma tela com dados
                    diferentes, exemplo Profile(name="X") e Profile(name="Y")

                 */

                val profile = scopedBackStackEntry.toRoute<NavRoute.ProfileScreenRoute>()
                ProfileScreen(profile.profile)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarComponent(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

