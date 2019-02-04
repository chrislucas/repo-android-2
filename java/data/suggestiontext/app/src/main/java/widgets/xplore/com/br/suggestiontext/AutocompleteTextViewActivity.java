package widgets.xplore.com.br.suggestiontext;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AutocompleteTextViewActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteSample;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    private ArrayAdapter<String> adapterAutoCompleteSample;

    List<String> words = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete_text_view);
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("text.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringTokenizer tk;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 0)  {
                    tk = new StringTokenizer(line, " ");
                    while (tk.hasMoreTokens())
                        words.add(tk.nextToken());
                }
            }
            autoCompleteSample = findViewById(R.id.autocomplete_sample);
            adapterAutoCompleteSample = new ArrayAdapter<String>(this
                    , android.R.layout.simple_dropdown_item_1line
                    , words);
            autoCompleteSample.setAdapter(adapterAutoCompleteSample);


            FilterText [] filters = new FilterText[1];
            filters[0] = new FilterText();

            autoCompleteSample.setFilters(filters);

            autoCompleteSample.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object object = parent.getItemAtPosition(position);
                    Log.i("ON_ITEM_CLICK", object.toString());
                }
            });

            /**
             * Threshold e a quantidade de caracteres minimos para a funcao de autocomplete
             * comecar a funcionar
             * */
            autoCompleteSample.setThreshold(1);

            autoCompleteSample.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(AutocompleteTextViewActivity.this
                            , String.format("Selecionado: %s", words.get(position)), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    if (inputMethodManager != null && getCurrentFocus() != null) {
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                }
            });

            multiAutoCompleteTextView = findViewById(R.id.multi_autocomplete_sample);
            multiAutoCompleteTextView.setAdapter(adapterAutoCompleteSample);
            multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
            multiAutoCompleteTextView.setThreshold(1);
        }
        catch (IOException e) {
            Log.e("EXCP", e.getMessage());
        }

    }
}
