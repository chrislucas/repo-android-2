package mobile.xplorer.studymultiprocess.handler.old

import android.os.Handler
import android.os.Looper
import android.os.Message


abstract class AbstractHandlerTest : Handler() {

    abstract fun processMessage(message: Message?)

    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)
        processMessage(msg)
    }
}