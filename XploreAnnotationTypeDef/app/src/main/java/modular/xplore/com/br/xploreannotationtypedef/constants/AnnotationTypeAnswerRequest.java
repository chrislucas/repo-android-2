package modular.xplore.com.br.xploreannotationtypedef.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by r028367 on 26/02/2018.
 */

public class AnnotationTypeAnswerRequest {

    public static final int ACCEPT = 0x01;
    public static final int REJECT = 0x02;

    @IntDef({ACCEPT, REJECT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnswerRequest {}

}
