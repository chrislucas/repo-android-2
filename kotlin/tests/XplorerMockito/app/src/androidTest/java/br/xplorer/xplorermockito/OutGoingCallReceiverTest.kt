package br.xplorer.xplorermockito

import android.content.Context
import android.content.Intent
import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import kotlin.concurrent.timer

class OutGoingCallReceiverTest {

    companion object {
        const val FIXED_PHONE_NUMBER = "46460412"
    }
    
    @Mock
    lateinit var  outGoingReceiver: OutGoingCallReceiver

    @Mock
    lateinit var context: Context


    @Test fun testOutGoingReceiver() {
        val intent = Intent(Intent.ACTION_NEW_OUTGOING_CALL)
        intent.putExtra(Intent.EXTRA_PHONE_NUMBER, FIXED_PHONE_NUMBER)
        outGoingReceiver.onReceive(context, intent)
        Assert.assertNull(outGoingReceiver.resultData)

        val argument = ArgumentCaptor.forClass(Intent::class.java)
        Mockito.verify(context, times(1))
            .startActivity(argument.capture())

        val receivedIntent = argument.value
        Assert.assertNull(receivedIntent.action)

        Assert.assertEquals(FIXED_PHONE_NUMBER
            , receivedIntent.getStringExtra(OutGoingCallReceiver.BUNDLE_PHONE_NUMBER))

        Assert.assertTrue(receivedIntent.flags and Intent.FLAG_ACTIVITY_NEW_TASK != 0)

    }
}