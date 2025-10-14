package com.br.justcomposelabs.tutorial.google.view.bottomsheet.aboutbottomsheetbehavior

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.justcomposelabs.R
import com.br.justcomposelabs.databinding.ActivityMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.jvm.java

class MenuActivity : AppCompatActivity() {
    private val binding: ActivityMenuBinding by lazy { ActivityMenuBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.run {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            btnModalFragment.setOnClickListener {
                val bottomSheetFragment = SimpleBottomSheetDialogFragment(
                    DefaultBottomSheetDialogFragment()
                )
                bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
            }

            btnPersistent.setOnClickListener {
                startActivity(
                    Intent(
                        this@MenuActivity,
                        PersistentBottomSheetViewActivity::class.java
                    )
                )
            }

            btnModal.setOnClickListener {
                val dialogView = layoutInflater.inflate(R.layout.simple_bottom_sheet_dialog, root)
                val bottomSheetDialog = BottomSheetDialog(this@MenuActivity)
                bottomSheetDialog.setContentView(dialogView)
                bottomSheetDialog.show()
            }
        }
    }
}