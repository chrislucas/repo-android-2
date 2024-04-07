package com.br.features.tutorials.google.flowtest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.labsusingcoroutines.R

/**
 * https://developer.android.com/kotlin/flow/test
 *
 * - A forma como testamos unidades ou módulos qu se comunicam
 * com Flow depende o componente que será testado usa o flow
 * como input ou output
 *      - Se o componente a ser testado oberva um flow (input), podemos gerar
 *      flows com uma dependencia fake/mock que podemos controlar
 *
 *      - Se a unidade que será testada expoe um flow, podemos ler e verificar 1
 *      ou mais items emitidos pelo flow
 *
 * Kotlin Flows Testing Guide
 * https://betulnecanli.medium.com/flow-testing-full-guide-a3e0980f66c8
 */
class FlowTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flow_test)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}