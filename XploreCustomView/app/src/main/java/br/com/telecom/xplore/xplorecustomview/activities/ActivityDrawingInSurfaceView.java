package br.com.telecom.xplore.xplorecustomview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import br.com.telecom.xplore.xplorecustomview.R;
import br.com.telecom.xplore.xplorecustomview.customview.SurfaceViewDrawingLine;

public class ActivityDrawingInSurfaceView extends AppCompatActivity {

    private SurfaceViewDrawingLine surfaceViewDrawingLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_in_surface_view);
        surfaceViewDrawingLine = findViewById(R.id.surface_view_drawing_line);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
