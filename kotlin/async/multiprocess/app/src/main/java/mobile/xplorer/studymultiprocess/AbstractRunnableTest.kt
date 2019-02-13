package mobile.xplorer.studymultiprocess

import android.os.Looper

abstract class AbstractRunnableTest : Runnable {

    abstract fun execute()

    override fun run() {
        execute()
        Looper.myLooper()?.quit()
    }
}