package slidingpanellayout.xplore.com.br.xploredevice.utils.wrapperproviders;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;


/**
 * Created by r028367 on 29/01/2018.
 */

public class WrapperContentProvider {

    public interface CProvider {
        void process(Cursor cursor, String [] columns);
    }

    public static final void executeSimpleQuery(ContentProviderClient contentProviderClient, CProvider cProvider,  Uri uri, String [] projectionFields
            , String expressionSelectionFilterWhere, String [] selectionArgs, String sortOrder) {
        try {
            Cursor cursor = contentProviderClient.query(uri, projectionFields, expressionSelectionFilterWhere, selectionArgs, sortOrder);
            if(cursor != null) {
                cProvider.process(cursor, projectionFields);
            }
        } catch (RemoteException e) {
            Log.e("REMOTE_EXCEP", e.getMessage());
        }
    }

    public static ContentProviderClient getContentProviderClient(Context context, Uri uri) {
        return context.getContentResolver().acquireContentProviderClient(uri);
    }

    public static Cursor executeSimpleQuery(Context context, Uri uri, String [] projectionFields
            , String expressionSelectionFilterWhere, String [] selectionArgs, String sortOrder) {
        return context.getContentResolver().query(uri, projectionFields, expressionSelectionFilterWhere, selectionArgs, sortOrder);
    }

    public static Uri getUriWithAppendedId(Uri uri, long id) {
        return ContentUris.withAppendedId(uri, id);
    }

}
