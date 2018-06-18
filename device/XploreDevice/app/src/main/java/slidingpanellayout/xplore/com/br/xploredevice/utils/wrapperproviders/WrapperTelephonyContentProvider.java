package slidingpanellayout.xplore.com.br.xploredevice.utils.wrapperproviders;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;


/**
 * Created by r028367 on 29/01/2018.
 */

public class WrapperTelephonyContentProvider {


    public static class CProviderConversation implements WrapperContentProvider.CProvider {

        public static final String [] COLUMNS = new String [] {
             Telephony.Sms.Conversations.SNIPPET
            ,Telephony.Sms.Conversations.MESSAGE_COUNT
        };

        @Override
        public void process(Cursor cursor, String[] columns) {
            if(cursor.moveToFirst()) {
                //showFields(cursor.getColumnNames());
                while (cursor.moveToNext()) {
                    String snippet = cursor.getString(cursor.getColumnIndex(COLUMNS[0]));
                    String messageCount = cursor.getString(cursor.getColumnIndex(COLUMNS[1]));
                    Log.i("PROCESS_FIELDS", String.format("Snippet: %s.\nMessageCount: %s."
                            , snippet, messageCount));
                }
            }
        }

        private void showFields(String [] fieldNames) {
            StringBuilder sb = new StringBuilder();
            for(String field : fieldNames)
                sb.append(String.format("%s%s", sb.length() == 0 ? "" : " ", field));
            Log.i("FIELDS", sb.toString());
        }
    }


    public static class CProviderSms implements WrapperContentProvider.CProvider {
        public static final String [] COLUMNS = new String [] {
             Telephony.Sms.ADDRESS
            ,Telephony.Sms.SUBJECT
            ,Telephony.Sms.BODY
            ,Telephony.Sms.SERVICE_CENTER
        };

        @Override
        public void process(Cursor cursor, String[] columns) {
            //showFields(cursor.getColumnNames());
            while (cursor.moveToNext()) {
                String address = cursor.getString(cursor.getColumnIndex(COLUMNS[0]));
                String subject = cursor.getString(cursor.getColumnIndex(COLUMNS[1]));
                String body = cursor.getString(cursor.getColumnIndex(COLUMNS[2]));
                String serviceCenter = cursor.getString(cursor.getColumnIndex(COLUMNS[3]));
                Log.i("PROCESS_FIELDS", String.format("Address: %s.\nSubject: %s.\nBody: %s.\nService Center: %s."
                        , address, subject, body, serviceCenter));
            }
        }

        private void showFields(String [] fieldNames) {
            StringBuilder sb = new StringBuilder();
            for(String field : fieldNames)
                sb.append(String.format("%s%s", sb.length() == 0 ? "" : " ", field));
            Log.i("FIELDS", sb.toString());
        }
    }


    public static class CProviderCarriers implements WrapperContentProvider.CProvider {

        public static final String [] COLUMNS = new String [] {
             Telephony.Carriers.APN
            ,Telephony.Carriers.NAME
            ,Telephony.Carriers.PROXY
            ,Telephony.Carriers.PROTOCOL
            ,Telephony.Carriers.PORT
            ,Telephony.Carriers.SERVER
            ,Telephony.Carriers.USER
            ,Telephony.Carriers.MCC // Mobile Country Code (MCC).
            ,Telephony.Carriers.MNC // Mobile Network Code (MNC).
        };

        @Override
        public void process(Cursor cursor, String[] columns) {
            if(cursor.moveToFirst()) {
                while (cursor.moveToNext()) {

                }
            }
        }
    };


    public static void executeSimpleQuery(Context context, Uri uri, WrapperContentProvider.CProvider cProvider, String [] projection
            , String selectionFilterWhere, String [] selectionArgs, String sortOrder) {
        Cursor cursor = WrapperContentProvider.executeSimpleQuery(context, uri
                , projection, selectionFilterWhere, selectionArgs, sortOrder);
        if(cursor != null) {
            cProvider.process(cursor, projection);
        }
    }
}
