package br.com.telecom.xplore.xplorecustomview.activities;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import br.com.telecom.xplore.xplorecustomview.R;
import br.com.telecom.xplore.xplorecustomview.customview.PieChart;

public class ActivityPieChartSample extends AppCompatActivity implements PieChart.OnCurrentItemChangedListener {

    @Override
    public void OnCurrentItemChanged(PieChart source, int currentItem) {
        Log.i("PIE_CHART_ITEM_CHANGED", String.valueOf(currentItem));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_sample);
        Resources res = getResources();
        final PieChart pie = this.findViewById(R.id.Pie);
        pie.setOnCurrentItemChangedListener(this);
        pie.addItem("Agamemnon", 2, res.getColor(R.color.seafoam));
        pie.addItem("Bocephus", 3.5f, res.getColor(R.color.chartreuse));
        pie.addItem("Calliope", 2.5f, res.getColor(R.color.emerald));
        pie.addItem("Daedalus", 3, res.getColor(R.color.bluegrass));
        pie.addItem("Euripides", 1, res.getColor(R.color.turquoise));
        pie.addItem("Ganymede", 3, res.getColor(R.color.slate));
        findViewById(R.id.Reset).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pie.setCurrentItem(0);
            }
        });
    }
}
