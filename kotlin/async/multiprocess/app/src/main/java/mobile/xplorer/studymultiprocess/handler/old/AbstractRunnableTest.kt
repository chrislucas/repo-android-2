package mobile.xplorer.studymultiprocess.handler.old

import android.os.Looper

abstract class AbstractRunnableTest : Runnable {

    abstract fun execute()

    override fun run() {
        execute()
        Looper.myLooper()?.quit()
    }
}