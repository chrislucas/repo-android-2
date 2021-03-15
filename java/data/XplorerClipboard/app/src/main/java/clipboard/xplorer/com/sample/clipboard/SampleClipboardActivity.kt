package clipboard.xplorer.com.sample.clipboard

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SampleClipboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_clipboard)

        ClipboardUtils.addSimpleTextToClipBoardWithoutVerification(
                "CONTENT_CLIP_BOARD", "UM TEXTO QUALQUER", this)

        val content = ClipboardUtils.getTextFromClipBoard(this)

        Log.i("CONTENT_CLIP_BOARD", content ?: "NENHUM CONTEUDO NO CLIPBOARD")
    }

    override fun onDestroy() {
        ClipboardUtils.removeDataFromClipBoard(this)
        super.onDestroy()
    }
}