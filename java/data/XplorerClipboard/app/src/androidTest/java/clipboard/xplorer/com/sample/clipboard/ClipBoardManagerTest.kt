package clipboard.xplorer.com.sample.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.rule.IntentsTestRule

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import clipboard.xplorer.com.sample.ForInstrumentedActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ClipBoardManagerTest {


    @get:Rule
    val rule = IntentsTestRule(ForInstrumentedActivity::class.java, false, false)

    @Test
    fun testIfContentTextIsKeptOnTheClipBoard() {
        rule.launchActivity(Intent())
        Handler(Looper.getMainLooper()).post {
            // Context of the app under test.
            val appContext = InstrumentationRegistry.getInstrumentation().context

            val clipBoardManager = appContext.getSystemService(Context.CLIPBOARD_SERVICE)
                    as ClipboardManager

            clipBoardManager.setPrimaryClip(ClipData.newPlainText("", "TEXT"))

            val content = ClipboardUtils.getTextFromClipBoard(context = appContext)

            assertEquals( "TEXT", content ?: "")
        }
    }
}
