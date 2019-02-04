package viewpage.xplorer.com.xplorerviewpager.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import viewpage.xplorer.com.xplorerviewpager.R;
import viewpage.xplorer.com.xplorerviewpager.fragments.FragmentWithViewPage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        if (fm != null) {
            fm.beginTransaction()
                    .replace(R.id.canvas_replace, FragmentWithViewPage.newInstance(10))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
