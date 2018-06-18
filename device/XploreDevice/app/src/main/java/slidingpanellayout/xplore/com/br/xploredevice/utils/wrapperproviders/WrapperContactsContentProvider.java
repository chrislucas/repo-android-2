package slidingpanellayout.xplore.com.br.xploredevice.utils.wrapperproviders;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
            ,ContactsContract.RawContacts.LAST_TIME_CONTACTED
        };

        @Override
        public void process(@NonNull Cursor cursor, String[] columns) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss", Locale.getDefault());
            if(cursor.moveToFirst()) {
                while (cursor.moveToNext()) {
                    int contactId = cursor.getInt(cursor.getColumnIndex(COLUMNS[0]));
                    String accountName = cursor.getString(cursor.getColumnIndex(COLUMNS[1]));
                    String accountType = cursor.getString(cursor.getColumnIndex(COLUMNS[2]));
                    String alternativeName = cursor.getString(cursor.getColumnIndex(COLUMNS[3]));
                    String sourceName = cursor.getString(cursor.getColumnIndex(COLUMNS[4]));
                    String primaryName = cursor.getString(cursor.getColumnIndex(COLUMNS[5]));
                    int starred = cursor.getInt(cursor.getColumnIndex(COLUMNS[6]));
                    int lastTimeContact = cursor.getInt(cursor.getColumnIndex(COLUMNS[7]));
                    Log.i("RAW_CONTACTS", String.format("CONTACT_ID: %d.\nACCOUNT_NAME: %s." +
                                    "\nACCOUNT_TYPE: %s.\nDISPLAY_NAME_ALTERNATIVE: %s." +
                                    "\nDISPLAY_NAME_SOURCE: %s.\nDISPLAY_NAME_PRIMARY: %s." +
                                    "\nSTARRED: %d.\nLAST_TIME_CONTACTED: %s.\n"
                            , contactId
                            , accountName
                            , accountType
                            , alternativeName
                            , sourceName
                            , primaryName
                            , starred
                            , simpleDateFormat.format(new Date(lastTimeContact))
                            )
                    );
                }
            }
        }
    }

    public static class CProviderPhoneLookup {
        public static final String [] COLUMNS = {
                ContactsContract.PhoneLookup._ID
                //,ContactsContract.PhoneLookup.CONTACT_ID
                //,ContactsContract.PhoneLookup.DATA_ID
                ,ContactsContract.PhoneLookup.DISPLAY_NAME
                ,ContactsContract.PhoneLookup.DISPLAY_NAME_ALTERNATIVE
                ,ContactsContract.PhoneLookup.DISPLAY_NAME_SOURCE
                ,ContactsContract.PhoneLookup.DISPLAY_NAME_PRIMARY
                ,ContactsContract.PhoneLookup.HAS_PHONE_NUMBER
                ,ContactsContract.PhoneLookup.NUMBER
                ,ContactsContract.PhoneLookup.CONTACT_LAST_UPDATED_TIMESTAMP
                ,ContactsContract.PhoneLookup.NORMALIZED_NUMBER
                ,ContactsContract.PhoneLookup.LAST_TIME_CONTACTED
        };
    }

    public static class CProviderPhone implements WrapperContentProvider.CProvider {

        public static final String [] COLUMNS = new String [] {
             ContactsContract.CommonDataKinds.Phone.NUMBER
            ,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            ,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE
            ,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_SOURCE
            ,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY
            ,ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER
            ,ContactsContract.CommonDataKinds.Phone.NUMBER
            ,ContactsContract.CommonDataKinds.Phone.CONTACT_LAST_UPDATED_TIMESTAMP
            ,ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER
            ,ContactsContract.CommonDataKinds.Phone.LAST_TIME_CONTACTED
        };

        @Override
        public void process(Cursor cursor, String[] columns) {
            if(cursor.moveToFirst()) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss", Locale.getDefault());
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex(COLUMNS[0]));
                    String name = cursor.getString(cursor.getColumnIndex(COLUMNS[1]));
                    String alternativeName = cursor.getString(cursor.getColumnIndex(COLUMNS[2]));
                    String sourceName = cursor.getString(cursor.getColumnIndex(COLUMNS[3]));
                    String primaryName = cursor.getString(cursor.getColumnIndex(COLUMNS[4]));
                    int hasNumber = cursor.getInt(cursor.getColumnIndex(COLUMNS[5]));
                    String number = cursor.getString(cursor.getColumnIndex(COLUMNS[6]));

                    long contactLastUpdateTimeStamp = cursor.getLong(cursor.getColumnIndex(COLUMNS[7]));
                    long lastTimeContacted = cursor.getLong(cursor.getColumnIndex(COLUMNS[9]));

                    String message = String.format(Locale.getDefault(),"_ID: %d.\nDISPLAY_NAME: %s.\n" +
                                    "DISPLAY_NAME_ALTERNATIVE: %s.\nDISPLAY_NAME_SOURCE: %s.\n" +
                                    "DISPLAY_NAME_PRIMARY: %s.\nHAS_PHONE_NUMBER; %d.\nNUMBER: %s.\n" +
                                    "CONTACT_LAST_UPDATED_TIMESTAMP: %s.\nLAST_TIME_CONTACTED: %s.\n"
                            , id
                            , name
                            , alternativeName
                            , sourceName
                            , primaryName
                            , hasNumber
                            , number
                            , simpleDateFormat.format(new Date(contactLastUpdateTimeStamp))
                            , simpleDateFormat.format(new Date(lastTimeContacted))
                    );
                    Log.i("RAW_CONTACTS_PHONE", message);
                }
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
