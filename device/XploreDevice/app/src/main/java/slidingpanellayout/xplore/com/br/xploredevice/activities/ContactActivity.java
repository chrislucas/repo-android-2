package slidingpanellayout.xplore.com.br.xploredevice.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import slidingpanellayout.xplore.com.br.xploredevice.R;
import slidingpanellayout.xplore.com.br.xploredevice.utils.wrapperproviders.WrapperContactsContentProvider;

public class ContactActivity extends AppCompatActivity {

    final String [] permissions = new String[]  {
         Manifest.permission.READ_CONTACTS
        ,Manifest.permission.WRITE_CONTACTS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ArrayList<String> p = new ArrayList<>();
        for (String permission : permissions) {
            if(ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                p.add(permission);
            }
        }
        if(p.size() > 0) {
            String [] array = new String[p.size()];
            for (int i = 0; i < p.size() ; i++)
                array[i] = p.get(i);

            ActivityCompat.requestPermissions(this, array, 0x0f);
        }
        else
            getRawContacts();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0x0f && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getRawContacts();
        }
    }

    private void getRawContacts() {
        WrapperContactsContentProvider.executeSimpleQuery(
                this
            , ContactsContract.RawContacts.CONTENT_URI
            , new WrapperContactsContentProvider.CProviderRawContacts()
            , WrapperContactsContentProvider.CProviderRawContacts.COLUMNS
            , null
            , null
            , null
        );
    }

    private void getDataPhone() {
        // ContactsContract.PhoneLookup.CONTENT_FILTER_URI
        WrapperContactsContentProvider.executeSimpleQuery(
                this
                , ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                , new WrapperContactsContentProvider.CProviderPhone()
                , WrapperContactsContentProvider.CProviderPhone.COLUMNS
                , null
                , null
                , null
        );
    }
}
