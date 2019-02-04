package notification.xplorer.copypasttextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private EditText editText;
    private TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView1 = findViewById(R.id.text_view_to_copy);
        registerForContextMenu(textView1);
        editText = findViewById(R.id.edit_text);
        registerForContextMenu(editText);

        textView2 = findViewById(R.id.text_view_to_copy_2);
        registerForContextMenu(textView2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(menuInfo != null) {

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.text_view_to_copy:
                break;

            case R.id.edit_text:
                break;

            case R.id.text_view_to_copy_2:
                break;
        }

        return true;
    }
}
