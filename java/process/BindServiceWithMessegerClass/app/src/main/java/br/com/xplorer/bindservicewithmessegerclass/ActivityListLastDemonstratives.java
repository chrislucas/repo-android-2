package br.com.xplorer.bindservicewithmessegerclass;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.xplorer.bindservicewithmessegerclass.adapter.AdapterRecycleViewLatestDemonstratives;

public class ActivityListLastDemonstratives extends AppCompatActivity {


    private RecyclerView recyclerView;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM/YYYY", Locale.getDefault());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_last_demonstratives);
        recyclerView = findViewById(R.id.list_last_demonstratives);
        AdapterRecycleViewLatestDemonstratives adapter = new AdapterRecycleViewLatestDemonstratives(getLastMonths(6));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private String [] getLastMonths(int count) {
        long currentDate = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentDate);
        String [] lastMonths = new String[count];
        for (int i = 1; i <count; i++) {
            calendar.add(Calendar.MONTH, -i);
            lastMonths[i-1] = simpleDateFormat.format(calendar.getTime());
        }
        return lastMonths;
    }
}
