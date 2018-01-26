package slidingpanellayout.xplore.com.br.xploredevice.utils;

import android.content.Context;
import android.database.Cursor;

import android.net.Uri;
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

public class UserDictContentProvider {

    public static final String [] COLUMS = { Words.WORD, Words.FREQUENCY, Words.LOCALE, Words.APP_ID, Words._ID };

    public static final String ORDER_BY_DESC [] = new String [COLUMS.length];
    public static final String ORDER_BY_ASC []  = new String [COLUMS.length];

    static {
        for (int i = 0; i < COLUMS.length ; i++) {
            ORDER_BY_DESC[i] = COLUMS[i] + " DESC";
            ORDER_BY_ASC[i] = COLUMS[i] + " ASC";
        }
    }

    public static Cursor makeSimpleQuery(Context context, Uri uri, String [] projection
            , String selectionFilterWhere, String [] selectionArgs, String sortOrder) {
        return context.getContentResolver().query(uri, projection, selectionFilterWhere, selectionArgs, sortOrder);
    }

    public static void makeSimpleQuery(Context context
            , String [] projection, String selectionFilterWhere, String [] selectionFilterArgs, String sortOrder) {
        Cursor cursor = makeSimpleQuery(
              context
            , UserDictionary.Words.CONTENT_URI
            , projection
            , selectionFilterWhere
            , selectionFilterArgs
            , sortOrder
        );

        if(cursor != null && cursor.moveToFirst()) {
            int countResults = cursor.getCount();
            Log.i("CURSOR_RESULT", String.valueOf(countResults));
            while ( cursor.moveToNext() ) {
                String word = cursor.getString(cursor.getColumnIndexOrThrow(COLUMS[0]));
                long frequency = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMS[1]));
                String locale = cursor.getString(cursor.getColumnIndexOrThrow(COLUMS[2]));
                int appId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMS[3]));
            }
        }

        else if(cursor != null){
            int countResults = cursor.getCount();
            Log.i("CURSOR_RESULT", String.valueOf(countResults));
        }
    }


}
