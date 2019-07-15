package mobile.xplorer.studymultiprocess.activities

import android.annotation.SuppressLint
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import mobile.xplorer.studymultiprocess.R

class TestMessageQueueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_message_queue)

        val messageQueue = Looper.myQueue()
        messageQueue.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                addIdleHandler {
                    Log.i("MY_MESSAGE_QUEUE", "IS_IDLE ? $isIdle")
                    isIdle
                }
            }

        }

        Handler().post {
            /**
             * Only one Looper may be created per thread
             * */
            if(Looper.myLooper() == null)
                Looper.prepare()

            /**
             * Sobre handler leak
             * http://tools.android.com/tips/lint-checks
             *
             * O uso do annotation HandlerLeak previne que o Handler
             * interno seja recolhido pelo GC.
             * Se o Handler interno estiver utilizando uma instancia
             * de MessageQueue ou Looper de uma Thread que não seja a MainThread, não
             * existe problemas,
             *
             * @link android.app.ActivityThread
             *
             * */
            val innerHandler = @SuppressLint("HandlerLeak")
            object: Handler (){
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    val innerQueue = Looper.myQueue()
                    val handlerQueue = this.looper.queue
                    Log.i("MY_MESSAGE_QUEUE", "SAME QUEUE ? ${innerQueue == messageQueue}")
                    Log.i("MY_MESSAGE_QUEUE", "SAME QUEUE ? ${handlerQueue == messageQueue}")
                    this.dump( {
                        str: String ->
                        Log.i("MY_MESSAGE_QUEUE_DUMP", str)

                    }, "DUMP_QUEUE")
                }
            }

            innerHandler.sendEmptyMessage(0)
            Looper.loop()
        }
    }
}
