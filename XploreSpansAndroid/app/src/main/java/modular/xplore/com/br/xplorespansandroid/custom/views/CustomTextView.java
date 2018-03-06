package modular.xplore.com.br.xplorespansandroid.custom.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;

import modular.xplore.com.br.xplorespansandroid.R;


/**
 * Created by r028367 on 27/07/2017.
 */

public class CustomTextView extends AppCompatTextView {

    private Context context;

    public CustomTextView(Context context) {
        super(context);
        loadDefault(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadDefault(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadDefault(context);
    }

    private void loadDefault(Context context) {
        try {
            this.context = context;
            Typeface typeface =  Typeface.createFromAsset(getContext().getAssets(), "fonts/minhafonte.ttf");
            setTypeface(typeface);
            //setTypeface(typeface, Typeface.NORMAL);
        }
        catch (Exception e) {
            Log.e("EXCP_LOADING_FONTS", e.getMessage());
        }
    }

    /**
     * Esse metodo é responsavel por encurtar o texto e adicionar uma String
     * ao final do texto encurtado como 'ver mais'
     * */
    public void collapseTextView(final int maxQLines, final String textToInsert) {
        final CustomTextView textViewRef = this;
        ViewTreeObserver viewTreeObserver = textViewRef.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            /**
             * CallbackImagePagerAdapter method to be invoked when the global layout state or the visibility of views
             * within the view tree changes
             */
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver innerViewTreeObserver = textViewRef.getViewTreeObserver();
                innerViewTreeObserver.removeOnGlobalLayoutListener(this);
                SpannableStringBuilder spannableStringBuilder = null;
                int linesOfTextView     = textViewRef.getLineCount();
                Layout layout           = textViewRef.getLayout();
                CharSequence targetText = textViewRef.getText();
                if(layout != null) {
                    if(maxQLines == 0) {
                        int qLines = layout.getLineEnd(0);
                        /**
                         * Pegar uma substring cujo tamanho seja, a quantidade de caracteres que cabe
                         * em 'qLines' - 'tamanho da string que deseja-se que apareca (Exemplo ... Ver mais)'
                         * */
                        String subStr = targetText.subSequence(0, qLines - textToInsert.length() + 1).toString();
                        // Concatenatar a substring com o texto que se quer que apareca depois (... Ver mais)
                        String finalString = String.format("%s %s", subStr, textToInsert);
                        textViewRef.setText(finalString);
                    }
                    else if(maxQLines <= linesOfTextView && maxQLines > 0) {
                        /*
                        * Layout.getLineEnd(int line)
                        * # https://developer.android.com/reference/android/text/Layout.html#getLineEnd(int)
                        * retorna a posicao do ultimo caracter de uma linha especifica
                        * */
                        int idxLastCharOfLine = layout.getLineEnd(maxQLines - 1);
                        String subStr = targetText.subSequence(0, idxLastCharOfLine - textToInsert.length() + 1).toString();
                        String finalString = String.format("%s %s", subStr, textToInsert);
                        textViewRef.setText(finalString);

                    }
                    else {
                        /**
                         * TextView.getLineCount
                         * # https://developer.android.com/reference/android/widget/TextView.html#getLineCount()
                         * Retonar o numero de linhas de texto ou 0 se o layout ainda nao foi construido
                         * */
                        linesOfTextView     = layout.getLineCount();
                        int idxLastCharOfLine    = layout.getLineEnd(linesOfTextView - 1);
                        String subStr       = targetText.subSequence(0, idxLastCharOfLine).toString();
                        if(subStr.length() < targetText.toString().length()) {
                            String finalString  = String.format("%s %s", subStr, textToInsert);
                            textViewRef.setText(finalString);
                        }
                    }

                    /**
                     * {@link android.text.method.MovementMethod} setMovementMethod
                     * https://developer.android.com/reference/android/text/method/MovementMethod.html
                     *
                     * Essa interface prove metodos para pegar o posicionamento do cursor, scrolling
                     * e selecao de texto
                     *
                     * {@link LinkMovementMethod}
                     * Especializacao de {@link android.text.method.ScrollingMovementMethod}
                     * que implementa {@link android.text.method.MovementMethod}
                     *
                     * Implementa suporte a cliques em texto-link
                     * */
                    //textViewRef.setMovementMethod(LinkMovementMethod.getInstance());

                    CharSequence finalCharsq = textViewRef.getText();
                    final float size = textViewRef.getTextSize() - 10.0f;
                    CharacterStyle characterStyle = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.default_black_text)) {
                        @Override
                        public void updateDrawState(TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                            ds.setTextSize(size);
                        }
                    };
                    spannableStringBuilder = buildSpannableStringBuilder(finalCharsq, textToInsert, characterStyle);
                    textViewRef.setText(spannableStringBuilder);
                }
            }
        });
    }


    /**
     * Adicionar um estilo a uma substring de uma string
     *
     * Com esse metodo podemos adicionar o estilo de ancora a uma substring de uma string, como
     * por exemplo 'Clique aqui neste <a>link<a/>'. A substring link pode receber o {@link CharacterStyle}
     * de um link, basta passar uma instancia da classe {@link ClickableSpan}
     * */
    public SpannableStringBuilder buildSpannableStringBuilder(final CharSequence subsetCharSeq, final String strToSpannable, CharacterStyle characterStyle) {
        // Texto reduzido que vai aparecer na View
        String subseqStr =  subsetCharSeq.toString();
        /**
         * {@link SpannableStringBuilder}
         * */
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(subsetCharSeq);
        /**
         * Indice de onde começa a substring
         * */
        int start = subseqStr.indexOf(strToSpannable);
        // Tamanho do texto que se quer que apareca para que o usuario clique e mostre mais do texto reduzido
        // exemplo 'Veja mais ...'
        int len = strToSpannable.length();
        // O texto reduzido contem a substring 'STR' por exemplo 'Ver mais ...'
        if(subseqStr.contains(strToSpannable)) {
            // se contem, adiciona um Estilo de texto que foi definido por parametro
            spannableStringBuilder.setSpan(characterStyle,start , start + len, 0);
        }
        return spannableStringBuilder;
    }

    public void justify() {

    }
}
