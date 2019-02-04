package modular.xplore.com.br.xploreannotationtypedef;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import modular.xplore.com.br.xploreannotationtypedef.constants.AnnotationTypeAnswerRequest;
import modular.xplore.com.br.xploreannotationtypedef.constants.AnnotationTypePossibleProblemsWithAcceesSystem;
import modular.xplore.com.br.xploreannotationtypedef.constants.AnnotationTypeWeatherSeasons;
import modular.xplore.com.br.xploreannotationtypedef.constants.Seasons;

public class MainActivity extends AppCompatActivity {


    /**
     * Interessante
     * {@link android.view.View#VISIBLE} esta vinculado ao Typedef @interface
     * Visibility
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("SEASON_TYPEDEF", String.format("%d %d %d %d"
                , Seasons.FALL, Seasons.SPRING, Seasons.SUMMER, Seasons.WINTER));

        Log.i("TYPE_ANSWER", String.format("%d %d"
                , AnnotationTypeAnswerRequest.ACCEPT, AnnotationTypeAnswerRequest.REJECT));


        AnnotationTypePossibleProblemsWithAcceesSystem n = new AnnotationTypePossibleProblemsWithAcceesSystem();
        n.doSomething(AnnotationTypePossibleProblemsWithAcceesSystem.INCORRECT_USERNAME);

        AnnotationTypeWeatherSeasons atws = new AnnotationTypeWeatherSeasons();
        atws.doSomething(Seasons.SPRING);

    }
}
