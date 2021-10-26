package mobile.xplorer.studymultiprocess.handler

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.os.Message

class LooperThread(val executor: Executor, name: String?) : Thread(name) {

    lateinit var handler : Handler
        private set

    interface Executor { fun execute(msg: Message?, handler: Handler) }

    override fun run() {
        /**
         * Condicional abaixo inicia o Looper
         * caso contrario, ao instanciar um handler dentro de uma Thread
         * eh lancada a seguinte exception
         * java.lang.RuntimeException: Can't create handler inside thread Thread
         * */
        if(Looper.myLooper() == null)
            Looper.prepare()
        handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                executor.execute(msg, this)
            }
        }
        Looper.loop()
    }
}