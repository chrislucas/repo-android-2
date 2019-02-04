package widgets.xplore.com.br.suggestiontext;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

import java.util.Locale;

public class FilterText implements InputFilter {

    /**
     * Start: inicio do CharSequence source
     * End: fim do CharSequence source
     *
     * Dstart: inicio da String inteira dentro do edittext
     * Dend: fim da String inteira dentro do edittext
     *
     * */
    @Override
    public CharSequence filter(CharSequence source
            , int start, int end, Spanned dest, int dstart, int dend) {


        String msg = String.format(Locale.getDefault()
                , "Start %d End %d DStart %d DEnd %d", start, end, dstart, dend);

        Log.i("FILTER_", msg);
        Log.i("FILTER_", source.toString());


        return source;
    }


}
