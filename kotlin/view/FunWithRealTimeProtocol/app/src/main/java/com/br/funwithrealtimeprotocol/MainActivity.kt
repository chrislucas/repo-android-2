package com.br.funwithrealtimeprotocol

import android.net.sip.SipManager
import android.net.sip.SipProfile
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithrealtimeprotocol.ui.theme.FunWithRealTimeProtocolTheme

class MainActivity : ComponentActivity() {

    /**
     * https://developer.android.com/guide/topics/connectivity/sip?hl=pt-br
     */

    /**
     * SipManager
     * Oferece apis para tarefas SIP
     *  - Iniciar uma conexao SIP
     *  - Fornecer acesso a servicos SIP relacionados
     */

    val sipManifest: SipManager? by lazy(LazyThreadSafetyMode.NONE) {
        SipManager.newInstance(this)
    }


    val sipProfile: SipProfile? by lazy {
        SipProfile.Builder("", "").build()
    }

    /**
     * SipProfile
     * Define um perfil SIP
     * Incluiindo uma conta SIP e infos de dominio e servido
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunWithRealTimeProtocolTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithRealTimeProtocolTheme {
        Greeting("Android")
    }
}