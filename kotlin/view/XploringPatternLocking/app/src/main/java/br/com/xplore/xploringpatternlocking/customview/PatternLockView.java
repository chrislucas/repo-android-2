package br.com.xplore.xploringpatternlocking.customview;

import static br.com.xplore.xploringpatternlocking.customview.PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS;
import static br.com.xplore.xploringpatternlocking.customview.PatternLockView.AspectRatio.ASPECT_RATIO_SQUARE;
import static br.com.xplore.xploringpatternlocking.customview.PatternLockView.AspectRatio.ASPECT_RATIO_WIDTH_BIAS;
import static br.com.xplore.xploringpatternlocking.customview.PatternLockView.PatternViewMode.AUTO_DRAW;
import static br.com.xplore.xploringpatternlocking.customview.PatternLockView.PatternViewMode.CORRECT;
import static br.com.xplore.xploringpatternlocking.customview.PatternLockView.PatternViewMode.WRONG;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by C_Luc on 14/01/2018.
 */

public class PatternLockView extends View {

    private static final int DEFAULT_PATTERN_DOT_COUNT = 3;
    private static final boolean PROFILE_DRAWING = false;
    private static final int MILLIS_PER_CIRCLE_ANIMATING = 700;
    // Amount of time (in millis) spent to animate a dot
    private static final int DEFAULT_DOT_ANIMATION_DURATION = 190;
    // Amount of time (in millis) spent to animate a path ends
    private static final int DEFAULT_PATH_END_ANIMATION_DURATION = 100;
    // This can be used to avoid updating the display for very small motions or noisy panels
    private static final float DEFAULT_DRAG_THRESHOLD = 0.0f;

    public PatternLockView(Context context) {
        super(context);
    }

    public PatternLockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PatternLockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * @IntDef permite criar um conjunto de valores pre-definidos de inteiros que podem ser usados
     * como anotacoes rotulando um atributo ou metodo. @IntDef pode ser usado no lugar de enums por exemplo
     * <p>
     * class T {
     * @ASPECT_RATION_SQUARE int p;
     * @ASPECT_RATION_SQUARE public int getP()
     * }
     * para usar essa anotacao devo criar uma interface e inicializar variaveis as variaveis
     * definidas dentro de @IntDef({VAR1, VAR2 ... VARN}) dentro da interface
     */
    @IntDef({ASPECT_RATIO_SQUARE, ASPECT_RATIO_WIDTH_BIAS, ASPECT_RATIO_HEIGHT_BIAS})
    /**
     * Onde a anotacao sera descartada ?
     *
     * Se nenhuma Retention for definida junto a uma anotacao a Retention padrao é a
     * RetentionPolicy.CLASS
     *
     * - CLASS: Anotacoes sao gravadas no arquivo .class pelo compilador mas nao sao mantidas
     * pela VM em tempo de execucao.
     *
     * - RUNTIME: Anotacoes sao gravadas no arqvuico .class e mantidas pela VM em tempo de execucao,
     * portanto podem ser lidas por reflexao
     *
     * - SOURCE: Anotacoes sao descartadas pelo compilador
     *
     * Os valores definidos dentro da interface podem ser definidos como atributos estaticos imutaveis
     * da classe se preferir
     * */
    @Retention(RetentionPolicy.SOURCE)
    /**
     * FunctionalInterface
     *
     * O @interface permite criar um tipo de definicao Typedef para usar nas classes do projeto
     * */
    public @interface AspectRatio {
        //
        int ASPECT_RATIO_SQUARE = 0;
        //
        int ASPECT_RATIO_WIDTH_BIAS = 1;
        //
        int ASPECT_RATIO_HEIGHT_BIAS = 2;
    }

    @IntDef({CORRECT, AUTO_DRAW, WRONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PatternViewMode {
        int CORRECT = 0, AUTO_DRAW = 1, WRONG = 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    public boolean onHoverEvent(MotionEvent event) {
        return super.onHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    /**
     * Testando o uso da anotacao @IntDef
     */
    @IntDef({POST, PUT, GET, DELETE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HttpMethod {
    }

    public static final int POST = 0;
    public static final int PUT = 2;
    public static final int GET = 1;
    public static final int DELETE = 3;

    @AspectRatio
    private int p;

    @HttpMethod
    private int method;

    public void test() {
        this.p = 123;
        this.method = 32;
        // o valor passado como argumento deve ser um dos valores definidos em @AspectRatio
        //test2(123);
        //test2(p);
        test2(p, method);
    }

    public void test2(@AspectRatio int aspectRatio, @HttpMethod int test) {

    }
}
