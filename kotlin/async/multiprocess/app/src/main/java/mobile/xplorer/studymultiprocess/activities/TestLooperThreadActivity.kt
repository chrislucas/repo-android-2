package mobile.xplorer.studymultiprocess.activities

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import mobile.xplorer.studymultiprocess.R
import mobile.xplorer.studymultiprocess.handler.LooperThread
import java.util.*

class TestLooperThreadActivity : AppCompatActivity() {


    lateinit var looper : LooperThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_looper_thread)
        val outerLooperQueue = Looper.myQueue()
        looper = LooperThread(object: LooperThread.Executor {
                override fun execute(msg: Message?, handler: Handler) {
                    if(msg != null) {
                        val data = msg.data
                        val time = data.getLong("TIME", -1)
                        val message = data.getString("MESSAGE", "EMPTY_STRING_MESSAGE")
                        Log.i("LOOPER_THREAD_MESSAGE", "$time, '$message'")
                        if(message == "quit") {
                            Looper.myLooper()?.quitSafely()
                            Log.i("LOOPER_THREAD_MESSAGE", "quit")
                        }
                    }
                    val innerLooperQueue = Looper.myQueue()
                    val handlerLooperQueue = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        handler.looper.queue
                    } else {
                       null
                    }
                    Log.i("MY_MESSAGE_QUEUE", "SAME QUEUE ? ${innerLooperQueue == outerLooperQueue}")
                    Log.i("MY_MESSAGE_QUEUE", "SAME QUEUE ? ${handlerLooperQueue == outerLooperQueue}")
                    Log.i("MY_MESSAGE_QUEUE", "SAME QUEUE ? ${handlerLooperQueue == outerLooperQueue}")
                }
            }
            , "MyLooperThread")

        looper.start()


        val buttonSendMessage = findViewById<Button>(R.id.send_message_in_thread)


        /**
         * Existem 2 formas de enviar uma mensagem a MessageQueue
         * 1) usando o metodo sendMessage da classe Handler
         * 2) usando o metodo post
         * */
        buttonSendMessage.setOnClickListener {
            val message = Message.obtain(looper.handler, 0x0f)
            val bundle = Bundle()
            bundle.putString("MESSAGE", "Uma mensagem qualquer")
            message.data = bundle
            Log.i("SEND_MESSAGE_VIA_BUTTON", "${looper.handler.sendMessage(message)}")
        }

        val buttonSendMessage2 = findViewById<Button>(R.id.send_message_in_thread_2)

        buttonSendMessage2.setOnClickListener {
            looper.handler.post { Log.i("MESSAGE", "Post Runnable") }
        }
    }

    override fun onResume() {
        super.onResume()
        val runnable = object : Runnable {
            override fun run() {
                val message = Message.obtain(looper.handler, 0x0f)
                val bundle = Bundle()
                bundle.putLong("TIME", Calendar.getInstance().timeInMillis)
                message.data = bundle
                Log.i("SEND_MESSAGE", "${looper.handler.sendMessage(message)}")
                looper.handler.postDelayed(this, 1000)
            }
        }
        looper.handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        val message = Message.obtain(looper.handler, 0x0f)
        val bundle = Bundle()
        bundle.putString("MESSAGE", "quit")
        message.data = bundle
        looper.handler.sendMessage(message)
    }
}
