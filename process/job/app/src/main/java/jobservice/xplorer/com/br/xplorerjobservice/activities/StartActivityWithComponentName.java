package jobservice.xplorer.com.br.xplorerjobservice.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import jobservice.xplorer.com.br.xplorerjobservice.R;

public class StartActivityWithComponentName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_with_componente_name);
        findViewById(R.id.start_activity_cn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName(getPackageName(), Main2Activity.class.getName());
                Log.i("START_ACTIVITY", componentName.toString());
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });
    }
}
