package clipboard.xplorer.com.xplorerclipboard

import android.content.ClipboardManager
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4


import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ClipBoardManagerTest {
    @Test
    fun testIfContentTextIsKeptOnTheClipBoard() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        val clipBoardManager = appContext.getSystemService(Context.CLIPBOARD_SERVICE)
                as ClipboardManager

    }
}
