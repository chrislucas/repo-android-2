package clipboard.xplorer.com.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import clipboard.xplorer.com.sample.clipboard.R

@VisibleForTesting
class ForInstrumentedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_instrumented)
    }
}