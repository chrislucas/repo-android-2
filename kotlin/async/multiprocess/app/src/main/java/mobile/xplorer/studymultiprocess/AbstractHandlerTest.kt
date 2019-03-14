package mobile.xplorer.studymultiprocess

import android.os.Handler
import android.os.Message


abstract class AbstractHandlerTest: Handler() {
    abstract fun processMessage(message: Message?)

    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)
        processMessage(msg)
    }
}