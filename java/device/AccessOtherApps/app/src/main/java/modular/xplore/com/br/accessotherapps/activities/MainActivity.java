package modular.xplore.com.br.accessotherapps.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import modular.xplore.com.br.accessotherapps.R;
import modular.xplore.com.br.accessotherapps.fragments.FragmentListAllApps;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_canvas_fragments, FragmentListAllApps.newInstance())
                .commit();
    }
}
