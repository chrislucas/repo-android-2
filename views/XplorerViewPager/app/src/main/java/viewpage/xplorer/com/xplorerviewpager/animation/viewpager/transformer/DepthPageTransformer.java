package viewpage.xplorer.com.xplorerviewpager.animation.viewpager.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by r028367 on 11/01/2017.
 */

public class DepthPageTransformer implements ViewPager.PageTransformer {


    private static final double MIN_SCALE = 0.75f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();

        /**
         * Quando o usuario fizer o movimento de arrastar a pagna para o lado
         * esquerdo podemos dar um efeito de desaparecimento parcial da pagina
         * pelo lado esquerdo
         * */
        // [-Infinity, -1) deslocando a pagina para esquerda para esquerda porem a pagina fica fora da vista
        if (position < -1) {
            page.setAlpha(0.0f);
        }

        // deslocamento para esquerda e a pagina esta sobre na vista do usuario
        else if (position <= 0) {
            page.setAlpha(1.0f);
            page.setTranslationX(0.0f);
            page.setScaleX(1.0f);
            page.setScaleY(1.0f);
        }

        else if(position <= 1) {
            page.setAlpha(1.0f - position);
            // deslocamento para
            page.setTranslationX(pageWidth * -position);
            float scaleFactor = (float) (MIN_SCALE + (1.0f - MIN_SCALE) * (1.0f - Math.abs(position)));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }

        // deslocamento para direita
        else {
            page.setAlpha(0);
        }
    }
}
