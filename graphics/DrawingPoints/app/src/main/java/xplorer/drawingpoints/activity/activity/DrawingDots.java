package xplorer.drawingpoints.activity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import xplorer.drawingpoints.R;
import xplorer.drawingpoints.activity.test.AutomaticDrawPointsOnView;
import xplorer.drawingpoints.activity.views.Dot2DView;

public class DrawingDots extends AppCompatActivity {

    private AutomaticDrawPointsOnView automaticDrawPointsOnView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_dots);
        Dot2DView dot2DView = findViewById(R.id.dot_view);
        automaticDrawPointsOnView = new AutomaticDrawPointsOnView(dot2DView, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        automaticDrawPointsOnView.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        automaticDrawPointsOnView.stop();
    }
}
