package modular.xplore.com.br.xploreannotationtypedef.constants;

import android.support.annotation.StringDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by r028367 on 27/02/2018.
 */

public class AnnotationTypePossibleProblemsWithAcceesSystem {


    @StringDef({INCORRECT_USERNAME, INCORRECT_PASSWORD, NO_INTERNET_CONNECTION})
    @Retention(RetentionPolicy.SOURCE)
    @interface PossibleProblemsWithAccessSystem {}


    public static final String INCORRECT_USERNAME = "INCORRECT_USERNAME";
    public static final String INCORRECT_PASSWORD = "INCORRECT_PASSWORD";
    public static final String NO_INTERNET_CONNECTION = "NO_INTERNET_CONNECTION";


    public void doSomething(@PossibleProblemsWithAccessSystem String possibleProble) {
        Log.i("PRINT_PROBLEM", possibleProble);
    }

}
