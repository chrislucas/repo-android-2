package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.Send
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding
import com.br.justcomposelabs.utils.generateRandomStrings
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import kotlin.random.Random

val mockedProfiles: ImmutableList<Profile> = buildList {
    repeat(100) {
        add(
            Profile(
                generateRandomStrings(2, " "),
                Random.nextLong(1, 99)
            )
        )
    }
}.toPersistentList()

@Composable
fun HomeScreen(
    onNavigationToProfile: (Profile) -> Unit = { Profile("", 0) }
) {
    val listState = rememberLazyListState()

    val canIGoToTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSizePadding()) {
        Scaffold(
            floatingActionButton = {
                AnimatedVisibility(visible = canIGoToTop) {
                    FloatingActionButton(onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                        }
                    }) {
                        Icon(
                            Icons.AutoMirrored.TwoTone.Send,
                            contentDescription = "Voltar ao topo"
                        )
                    }
                }
            }
        ) { valuesPadding ->
            LazyColumn(
                modifier = Modifier.padding(valuesPadding)
            ) {
                items(mockedProfiles, key = { it }) { profile ->
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 8.dp, bottom = 8.dp),
                        onClick = {
                            onNavigationToProfile(profile)
                        }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = profile.name,
                                style = TextStyle(
                                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                    fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
                                    fontWeight = MaterialTheme.typography.headlineSmall.fontWeight
                                ),
                                maxLines = 1
                            )
                            Text(
                                text = "${profile.age}",
                                style = TextStyle(
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
