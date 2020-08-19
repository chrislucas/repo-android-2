package clipboard.xplorer.com.sample.clipboard

import android.content.ClipData
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import android.os.Build

object ClipboardUtils {

    private fun providerClipBoardManager(context: Context) =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as (ClipboardManager)

    fun getTextFromClipBoard(context: Context) : String? {
        val clipBoardManager = providerClipBoardManager(context)

        return when {
             hasTextOnClipBoard(clipBoardManager) -> {
                clipBoardManager.primaryClip?.getItemAt(0)?.text.toString()
            }
            else -> {
                null
            }
        }
    }

    fun addSimpleTextToClipBoardWithoutVerification(label: String, content: String, context: Context) {
        providerClipBoardManager(context).setPrimaryClip(ClipData.newPlainText(label, content))
    }

    private fun hasTextOnClipBoard(clipBoardManager: ClipboardManager) : Boolean =
        clipBoardManager.hasPrimaryClip() &&
                clipBoardManager.primaryClipDescription?.hasMimeType(MIMETYPE_TEXT_PLAIN) ?: false


    fun removeDataFromClipBoard(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            providerClipBoardManager(context).clearPrimaryClip()
        }
    }
}