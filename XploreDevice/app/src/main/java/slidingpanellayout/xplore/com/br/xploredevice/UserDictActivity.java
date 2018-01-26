package slidingpanellayout.xplore.com.br.xploredevice;

import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import slidingpanellayout.xplore.com.br.xploredevice.utils.UserDictContentProvider;

public class UserDictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dict);
        UserDictContentProvider.makeSimpleQuery(this
                , UserDictContentProvider.COLUMS
                , null
                , new String [] {""}
                , null //UserDictContentProvider.ORDER_BY_ASC[0]
        );


        UserDictContentProvider.makeSimpleQuery(this
                , UserDictContentProvider.COLUMS
                , UserDictionary.Words.WORD + " = ?"
                , new String [] {"algoritmo"}
                , null
        );

    }
}
