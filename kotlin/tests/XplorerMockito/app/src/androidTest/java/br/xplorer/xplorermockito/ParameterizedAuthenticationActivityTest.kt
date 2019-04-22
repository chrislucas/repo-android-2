package br.xplorer.xplorermockito

import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class ParameterizedAuthenticationActivityTest {


    companion object {
        @Parameterized.Parameters
        fun data() : Array<Pair<String, String>> = arrayOf(
            "@chrisluccas" to "admin123"
            , "@mariafernades" to "teste123"
            , "@josegomes" to "senhaforte123"
        )
    }
}