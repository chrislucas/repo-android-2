package widgets.xplore.com.br.suggestiontext;

import android.widget.MultiAutoCompleteTextView;

public class SpaceTokenizer implements MultiAutoCompleteTextView.Tokenizer {

    @Override
    public int findTokenStart(CharSequence text, int cursor) {
        return 0;
    }

    @Override
    public int findTokenEnd(CharSequence text, int cursor) {
        return 0;
    }

    @Override
    public CharSequence terminateToken(CharSequence text) {
        return null;
    }
}
