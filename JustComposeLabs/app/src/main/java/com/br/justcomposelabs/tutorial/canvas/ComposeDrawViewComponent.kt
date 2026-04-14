package com.br.justcomposelabs.tutorial.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.withTranslation
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose

    Misconception about Kotlin @JvmOverloads for Android View Creation
    https://proandroiddev.com/misconception-about-kotlin-jvmoverloads-for-android-view-creation-cb88f432e1fe

 */
@Composable
fun ComposeDrawView(
    modifier: Modifier = Modifier,
    factory: (ctx: Context) -> View,
    update: (View) -> Unit = {}
) {
    AndroidView(
        modifier = modifier,
        factory = factory,
        update = update
    )
}

@Preview(name = "DrawViewDrawTextPreview", showBackground = true, showSystemUi = true)
@Composable
fun ComposeDrawViewDrawTextPreview() {
    JustComposeLabsTheme {
        ComposeDrawView(
            Modifier
                .fillMaxWidth()
                .systemBarsPadding()
                .statusBarsPadding()
                .wrapContentHeight(),
            factory = {
                DrawText(it).apply {
                    content = "hello world My Friend, How are you doing ?"
                }
            }
        )
    }
}

class DrawText @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(ctx, attrs, defStyleAttr) {

    companion object {
        // Razão para calcular o tamanho da fonte baseado na largura do View
        private const val TEXT_SIZE_RATIO = 0.06f
    }

    private var measuredViewWidth: Int = 0
    private var measuredViewHeight: Int = 0

    // Padding mínimo de 2dp em pixels
    private val minPaddingPx: Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        2f,
        resources.displayMetrics
    ).toInt()

    // Largura fixa do StaticLayout em pixels
    private val staticLayoutFixedWidth: Int = 500

    // Cache do displayText, tamanho de fonte e StaticLayout para evitar recriação
    private var cachedDisplayText: String = ""
    private var cachedTextSize: Float = 0f
    private var _cachedStaticLayout: StaticLayout? = null

    private fun createEmptyStaticLayout(): StaticLayout {
        return StaticLayout.Builder.obtain(
            "",
            0,
            0,
            textPaint,
            staticLayoutFixedWidth
        ).build()
    }

    var content: String = "*"
        set(value) {
            if (field != value) {
                field = value
                // Invalidar cache quando o conteúdo muda
                _cachedStaticLayout = null
                cachedTextSize = 0f
                cachedDisplayText = ""
                // Invalidar layout e view quando o conteúdo muda
                requestLayout()
                invalidate()
            }
        }

    // TextPaint uma subclasse de Paint para desenhar texto
    val textPaint = TextPaint(ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textAlign = Paint.Align.CENTER
    }

    // Paint para o background - reutilizável
    private val backgroundPaint = Paint().apply {
        color = Color.LTGRAY
        style = Paint.Style.FILL
    }

    /**
     * Cria ou retorna o StaticLayout em cache
     * Evita recriação desnecessária do layout e armazena o tamanho da fonte
     */
    private fun getOrCreateStaticLayout(
        textSize: Float,
        availableWidth: Int,
        availableHeight: Int
    ): StaticLayout {
        // Criar displayText baseado na largura e altura disponível
        val displayText = "w: $availableWidth, h: $availableHeight, content: $content"

        // Se o texto mudou ou o tamanho da fonte é diferente, recriar
        if (cachedDisplayText != displayText || cachedTextSize != textSize) {
            textPaint.textSize = textSize
            cachedTextSize = textSize // ← Armazenar tamanho da fonte
            cachedDisplayText = displayText
            _cachedStaticLayout = StaticLayout.Builder.obtain(
                displayText,
                0,
                displayText.length,
                textPaint,
                staticLayoutFixedWidth // Usar largura fixa
            ).build()
        }

        // Retornar o StaticLayout em cache ou criar um vazio se não existir
        return _cachedStaticLayout ?: createEmptyStaticLayout().also { _cachedStaticLayout = it }
    }

    /**
     * Calcula a altura desejada baseado na largura fixa do StaticLayout
     * Esta altura é utilizada para calcular a altura intrínseca do View
     * Inclui padding mínimo de 2dp em todos os lados
     * Garante que o texto caiba todo dentro do retângulo
     */
    private fun getDesiredHeight(availableWidth: Int): Int {
        if (availableWidth <= 0) return minPaddingPx * 2

        // Calcular tamanho de fonte baseado na largura
        val calculatedTextSize = availableWidth * TEXT_SIZE_RATIO

        // Obter ou criar StaticLayout (com cache)
        // Usar availableWidth como placeholder para altura já que ainda não conhecemos a altura desejada
        val staticLayout =
            getOrCreateStaticLayout(calculatedTextSize, availableWidth, availableWidth)

        // A altura desejada é a altura do StaticLayout mais padding superior e inferior
        val desiredHeight = staticLayout.height + (minPaddingPx * 2)
        return desiredHeight
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        measuredViewWidth = w
        measuredViewHeight = h
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Extrair modo de medição de largura (EXACTLY, AT_MOST ou UNSPECIFIED)
        // EXACTLY: tamanho exato foi definido pelo parent (ex: match_parent com tamanho fixo)
        // AT_MOST: tamanho máximo foi definido (ex: wrap_content)
        // UNSPECIFIED: nenhuma restrição de tamanho
        /*
            TODO estudar mais essa classe, explorar a classe MeasureSpec
         */
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        // Extrair tamanho de largura do MeasureSpec
        // Este é o tamanho máximo disponível ou exato, dependendo do modo
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        // Extrair modo de medição de altura (EXACTLY, AT_MOST ou UNSPECIFIED)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        // Extrair tamanho de altura do MeasureSpec
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // Resolver a largura final baseado no modo recebido
        val resolvedWidth = when (widthMode) {
            // Se EXACTLY: usar o tamanho exato do parent
            MeasureSpec.EXACTLY -> widthSize

            // Se AT_MOST: usar o mínimo entre o tamanho máximo do parent e nossa largura mínima sugerida
            // coerceAtLeast(300): garantir largura mínima de 300px
            MeasureSpec.AT_MOST -> minOf(widthSize, suggestedMinimumWidth.coerceAtLeast(300))

            // Se UNSPECIFIED: usar apenas nossa largura mínima sugerida
            else -> suggestedMinimumWidth.coerceAtLeast(300)
        }

        // Calcular altura desejada baseado na largura resolvida
        // Esta função considera o conteúdo (texto) para determinar quanto de espaço é necessário
        val desiredHeight = getDesiredHeight(resolvedWidth)

        // Resolver a altura final baseado no modo recebido
        val resolvedHeight = when (heightMode) {
            // Se EXACTLY: usar o tamanho exato do parent
            MeasureSpec.EXACTLY -> heightSize

            // Se AT_MOST: usar o mínimo entre o tamanho máximo do parent e nossa altura desejada
            // Garante que não usamos mais espaço que o necessário para o conteúdo
            MeasureSpec.AT_MOST -> minOf(heightSize, desiredHeight)

            // Se UNSPECIFIED: usar apenas nossa altura desejada baseada no conteúdo
            else -> desiredHeight
        }

        // Comunicar ao Android o tamanho final deste View
        // Isto é obrigatório: o parent depende desta informação para posicionar este View
        setMeasuredDimension(resolvedWidth, resolvedHeight)
    }

    override fun onDraw(canvas: Canvas) {
        // Desenhar background que cobre TODA a altura do View
        canvas.drawRect(
            0f,
            0f,
            measuredViewWidth.toFloat(),
            measuredViewHeight.toFloat(),
            backgroundPaint
        )

        // Obter ou criar StaticLayout (com cache) usando o tamanho de fonte armazenado
        val staticLayout =
            getOrCreateStaticLayout(cachedTextSize, measuredViewWidth, measuredViewHeight)

        // Calcular as coordenadas para centralizar dentro do View
        // X: (largura do view - largura real do texto) / 2
        val centerX = measuredViewWidth / 2f
        // Y: padding mínimo + (altura disponível - altura do texto) / 2
        val availableHeightForText = measuredViewHeight - (minPaddingPx * 2)
        val centerY = minPaddingPx + (availableHeightForText - staticLayout.height) / 2f

        // Desenhar usando withTranslation para centralizar
        canvas.withTranslation(centerX, centerY) {
            staticLayout.draw(this)
        }
    }
}
