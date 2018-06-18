package modular.xplore.com.br.xploreannotationtypedef.constants;

import android.util.Log;

/**
 * Created by r028367 on 27/02/2018.
 */

public class AnnotationTypeWeatherSeasons {


    public void doSomething(@Seasons int season) {
        Log.i("TYPEDEF_SEASON", String.valueOf(season));
    }

}
