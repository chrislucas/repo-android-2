package slidingpanellayout.xplore.com.br.slidingpanellayout.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import slidingpanellayout.xplore.com.br.slidingpanellayout.R;
import slidingpanellayout.xplore.com.br.slidingpanellayout.activities.fragments.FragmentInfoA;
import slidingpanellayout.xplore.com.br.slidingpanellayout.activities.fragments.FragmentInfoB;

public class SlidePanelFragment extends AppCompatActivity {

    private SlidingPaneLayout slidingPaneLayout;
    private FragmentManager fragmentManager;


    private class SlidePaneLayoutListener implements SlidingPaneLayout.PanelSlideListener {
        @Override
        public void onPanelSlide(View panel, float slideOffset) {
            Log.i("ON_PANEL_SLIDE", String.format("Offset %f", slideOffset));
        }

        @Override
        public void onPanelOpened(View panel) {
            Log.i("ON_PANEL_OPENED", "ON_PANEL_OPENED");
        }

        @Override
        public void onPanelClosed(View panel) {
            Log.i("ON_PANEL_CLOSED", "ON_PANEL_CLOSED");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_painel_fragment);

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.left_side_fragment, FragmentInfoA.newInstance())
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.right_side_fragment, FragmentInfoB.newInstance())
                .commit();


        slidingPaneLayout = findViewById(R.id.sliding_panel_layout_fragments);
        slidingPaneLayout.setPanelSlideListener(new SlidePaneLayoutListener());
        slidingPaneLayout.setParallaxDistance(100);
    }
}
