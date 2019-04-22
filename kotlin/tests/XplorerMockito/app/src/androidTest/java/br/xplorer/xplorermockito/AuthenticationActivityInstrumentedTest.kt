package br.xplorer.xplorermockito

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AuthenticationActivityInstrumentedTest {

    /*
    * {@link https://developer.android.com/reference/android/support/test/rule/ActivityTestRule}
    *
    * Regra que prove teste funcional numa unica Acitivty.
    *
    * A documentacao diz que: Quando a launchActivity estiver definida como TRUE
    * a Activity que esta sobre teste será iniciada antes de cada teste com anotacao
    * @Test e de cada metodo com a anotaacao @Before. Sera encerrada apos todos os testes e todos
    * os metodos com anotacao @After terminarem
    *
    * Fontes
    * https://www.vogella.com/tutorials/AndroidTesting/article.html#testingothercomponents
    * */

    @get:Rule
    val activityRule = ActivityTestRule(AuthenticationActivity::class.java)

    /**
     * Iniciando uma activitty
     * */
    //@Test fun testLaunchActivity() = activityRule.launchActivity(Intent(Intent.ACTION_PICK))


    /*
    *
    * */

    @Test fun doAuthenticationWithoutFillFields() {
        onView(withId(R.id.button_authentication)).perform(click())
    }

    /*
    * */

    @Test fun doAuthenticationFilledFields() {
        onView(withId(R.id.edit_text_user_id)).perform(typeText("@chrisluccas"), closeSoftKeyboard())
        onView(withId(R.id.edit_text_password)).perform(typeText("admin123"), closeSoftKeyboard())
        onView(withId(R.id.button_authentication)).perform(click())
    }
}