package com.br.funwithdatabinding.view.features.allfeatures.view

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


/*
  Todo escrever um teste com robolectric
  https://developer.android.com/training/testing/local-tests/robolectric?hl=pt-br
  - É possvel usar criar mocks/fakes que sao os chamados Shadows

  Recomendacoes

  - Usar o robolectric para test de comportamentos de components UI isolados. Esse
  testes checam o gerenciamento de estado e comportamento da UI sem interagir com
  dependencias externas.

  -

  Shadows Robolectric
  - https://robolectric.org/extending/
 */

@RunWith(AndroidJUnit4::class)
class AllFeaturesActivityTest {

    @Test
    fun `test`() {
        val scenario = ActivityScenario.launch(AllFeaturesActivity::class.java)

        scenario.onActivity {  }
    }
}