package jobservice.xplorer.com.br.xplorerjobservice;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivityWithComponenteName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_with_componente_name);
        findViewById(R.id.start_activity_cn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(getPackageName(), Main2Activity.class.getName()));
                startActivity(intent);
            }
        });
    }
}
