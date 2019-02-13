package mobile.xplorer.studymultiprocess

import android.os.Handler
import android.os.Looper

class LooperThead(private val runnable: AbstractRunnableTest, private val handler: AbstractHandlerTest): Thread() {

    override fun run() {
        super.run()
        Looper.prepare()
        handler.post(runnable)
        Looper.loop()
    }
}