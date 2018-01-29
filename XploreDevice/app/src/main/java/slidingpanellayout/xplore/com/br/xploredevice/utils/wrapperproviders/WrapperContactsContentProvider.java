package slidingpanellayout.xplore.com.br.xploredevice.utils.wrapperproviders;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by r028367 on 29/01/2018.
 */

public class WrapperContactsContentProvider {


    public static class CProviderRawContacts implements WrapperContentProvider.CProvider {

        public static final String [] COLUMNS = new String[]  {
             ContactsContract.RawContacts.CONTACT_ID
            ,ContactsContract.RawContacts.ACCOUNT_NAME
            ,ContactsContract.RawContacts.ACCOUNT_TYPE
            ,ContactsContract.RawContacts.DISPLAY_NAME_ALTERNATIVE
            ,ContactsContract.RawContacts.DISPLAY_NAME_SOURCE
            ,ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY
            ,ContactsContract.RawContacts.STARRED
        };

        @Override
        public void process(@NonNull Cursor cursor, String[] columns) {
            while (cursor.moveToNext()) {
                int contactId = cursor.getInt(cursor.getColumnIndex(COLUMNS[0]));
                String accountName = cursor.getString(cursor.getColumnIndex(COLUMNS[1]));
                String accountType = cursor.getString(cursor.getColumnIndex(COLUMNS[2]));
                String alternativeName = cursor.getString(cursor.getColumnIndex(COLUMNS[3]));
                String sourceName = cursor.getString(cursor.getColumnIndex(COLUMNS[4]));
                String primaryName = cursor.getString(cursor.getColumnIndex(COLUMNS[5]));
                int starred = cursor.getInt(cursor.getColumnIndex(COLUMNS[6]));
                Log.i("RAW_CONTACTS", String.format("CONTACT_ID: %d.\nACCOUNT_NAME: %s." +
                                "\nACCOUNT_TYPE: %s.\nDISPLAY_NAME_ALTERNATIVE: %s." +
                                "\nDISPLAY_NAME_SOURCE: %s.\nDISPLAY_NAME_PRIMARY: %s.\nSTARRED: %d.\n"
                        , contactId
                        , accountName
                        , accountType
                        , alternativeName
                        , sourceName
                        , primaryName
                        , starred
                    )
                );
            }
        }
    }

    public static void executeSimpleQuery(Context context, Uri uri, WrapperContentProvider.CProvider cProvider, String [] projection
            , String selectionFilterWhere, String [] selectionArgs, String sortOrder) {
        Cursor cursor = WrapperContentProvider.executeSimpleQuery(context, uri
                , projection, selectionFilterWhere, selectionArgs, sortOrder);
        if(cursor != null) {
            cProvider.process(cursor, projection);
        }
    }
}
