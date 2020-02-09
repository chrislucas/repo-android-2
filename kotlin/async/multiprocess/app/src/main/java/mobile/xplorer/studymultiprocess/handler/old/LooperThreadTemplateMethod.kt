package mobile.xplorer.studymultiprocess.handler.old


import android.os.Looper


class LooperThreadTemplateMethod(private val runnable: AbstractRunnableTest
                                 , private val handler: AbstractHandlerTest
): Thread() {

    override fun run() {
        super.run()
        if(Looper.myLooper() == null)
            Looper.prepare()
        handler.post(runnable)
        Looper.loop()
    }
}