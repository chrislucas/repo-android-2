package modular.xplore.com.br.xplorespansandroid.custom.views;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.util.AttributeSet;

/**
 * Created by r028367 on 05/03/2018.
 */

public class BulletTextView extends CustomTextView {

    public BulletTextView(Context context) {
        super(context);
        addBulletSpan();
    }

    public BulletTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addBulletSpan();
    }


    public void addBulletSpan() {
        CharSequence charSequence = getText();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        spannableStringBuilder.setSpan(new BulletSpan(BulletSpan.STANDARD_GAP_WIDTH), 0, charSequence.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    }


    public void addBulletSpan(int gapWidth) {
        CharSequence charSequence = getText();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        spannableStringBuilder.setSpan(new BulletSpan(gapWidth), 0, charSequence.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    }


}
