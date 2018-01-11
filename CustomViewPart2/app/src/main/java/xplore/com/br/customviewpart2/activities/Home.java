package xplore.com.br.customviewpart2.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;

import xplore.com.br.customviewpart2.BuildConfig;
import xplore.com.br.customviewpart2.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.i("BUILD_CONFIG", String.format(Locale.getDefault()
                , "Type: %s\nFlavor: %s\nVersion Name: %s"
                , BuildConfig.BUILD_TYPE
                , BuildConfig.FLAVOR
                , BuildConfig.VERSION_NAME
            )
        );

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Home.this, MainActivity.class));
            }
        }, 10000);
    }
}
