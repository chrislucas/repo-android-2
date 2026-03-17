package com.br.funwithjetpackcompose.tutorials.google.widget.glance

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import com.br.funwithjetpackcompose.tutorials.google.constraintlayout.FunWithConstraintLayoutActivity
import com.br.funwithjetpackcompose.tutorials.google.thinkingcompose.ThinkingInComposeRecompositionActivity

/*
    https://developer.android.com/develop/ui/compose/glance/create-app-widget
 */
class AppWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget
        get() = CustomAppWidget()
}


class CustomAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Layout()
        }
    }


    @Preview(showBackground = true)
    @Composable
    private fun Layout() {
        Column(
            modifier = GlanceModifier.fillMaxSize(),
            verticalAlignment = Alignment.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Abrir Activity", modifier = GlanceModifier.padding(12.dp))
            Row(horizontalAlignment = Alignment.CenterHorizontally){
                Button(text = "FunWithConstraintLayoutActivity", onClick = actionStartActivity<FunWithConstraintLayoutActivity>())
                Button(text = "ThinkingInComposeRecompositionActivity", onClick = actionStartActivity<ThinkingInComposeRecompositionActivity>())
            }
        }
    }
}