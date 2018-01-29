package slidingpanellayout.xplore.com.br.xploredevice.utils.wrapperproviders;

import android.content.Context;
import android.database.Cursor;

import android.provider.UserDictionary;
import android.util.Log;

import static android.provider.UserDictionary.Words;

/**
 * Created by r028367 on 26/01/2018.
 *
 * Uris de conteudo contem um nome que identifica o conjunto de informacoes que se quer abrigar
 * (como um nome de uma tabela), esse nome e chamado de AUTHORITY veja em {@link UserDictionary#AUTHORITY}
 * e um nome que aponta para uma tabela veja {@link UserDictionary.Words#CONTENT_URI}
 *
 */

public class WrapperUserDictionaryContentProvider {

    public static final String [] ALL_COLUMNS = { Words._ID, Words.WORD, Words.FREQUENCY, Words.LOCALE, Words.APP_ID};
    public static final String ORDER_BY_DESC [] = new String [ALL_COLUMNS.length];
    public static final String ORDER_BY_ASC [] = new String [ALL_COLUMNS.length];

    static {
        for (int i = 0; i < ALL_COLUMNS.length ; i++) {
            ORDER_BY_DESC[i] = ALL_COLUMNS[i] + " DESC";
            ORDER_BY_ASC[i] = ALL_COLUMNS[i] + " ASC";
        }
    }
    public static void makeSimpleQuery(Context context
            , String [] projectionFields, String selectionFilterWhere, String [] selectionFilterArgs, String sortOrder) {
        Cursor cursor = WrapperContentProvider.executeSimpleQuery(
              context
            , Words.CONTENT_URI
            , projectionFields
            , selectionFilterWhere
            , selectionFilterArgs
            , sortOrder
        );

        if(cursor != null && cursor.moveToFirst()) {
            int countResults = cursor.getCount();
            Log.i("CURSOR_RESULT", String.valueOf(countResults));
            while ( cursor.moveToNext() ) {
                String word = cursor.getString(cursor.getColumnIndexOrThrow(ALL_COLUMNS[0]));
                long frequency = cursor.getLong(cursor.getColumnIndexOrThrow(ALL_COLUMNS[1]));
                String locale = cursor.getString(cursor.getColumnIndexOrThrow(ALL_COLUMNS[2]));
                int appId = cursor.getInt(cursor.getColumnIndexOrThrow(ALL_COLUMNS[3]));
            }
        }

        else if(cursor != null){
            Log.i("CURSOR_RESULT", String.valueOf(cursor.getCount()));
        }
    }
}
