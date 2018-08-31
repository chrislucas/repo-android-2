package clipboard.xplorer.com.xplorerclipboard;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {


    private static final int REQ_PERMISSION = 0x0f;

    public void requestPermissions() {
        String permissions[] = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            permissions = new String [] {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS};
        }
        else {
            permissions = new String [] {Manifest.permission.READ_PHONE_STATE};
        }
        List<String> perm = new ArrayList<>();
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                perm.add(permission);
            }
        }

        if (perm.size() > 0) {
            int n = perm.size();
            permissions = new String[n];
            System.arraycopy(perm.toArray(), 0, permissions, 0, n);
            ActivityCompat.requestPermissions(this, permissions, REQ_PERMISSION);
        }
        else {
            getPhoneNumber();
        }
    }

    private void getPhoneNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }
        if (telephonyManager != null) {
            Log.i("TELEPHONY_MANAGER", String.format("SimSerialNumber: %s\nNumber: %s\nDevice Software Version: %s"
                        , telephonyManager.getSimSerialNumber()
                        , telephonyManager.getLine1Number()
                        , telephonyManager.getDeviceSoftwareVersion()
                    )

            );
            PersistableBundle persistableBundle = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                persistableBundle = telephonyManager.getCarrierConfig();
                for (String key : persistableBundle.keySet()) {
                    Object value = persistableBundle.get(key);
                    if (value != null) {
                        Log.i("TELEPHONY_MANAGER"
                                , String.format("Key: %s, Val: %s", key, value.toString()));
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ComponentName componentName = new ComponentName(this, ImplConnectionService.class);
                PhoneAccountHandle  phoneAccountHandle = new PhoneAccountHandle(componentName, ImplConnectionService.TAG);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    telephonyManager.createForPhoneAccountHandle(phoneAccountHandle);
                }
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhoneNumber();
            }
        }
    }

    private ClipboardManager.OnPrimaryClipChangedListener onPrimaryClipChangedListener;

    private ClipboardManager mClipboardManager;

    private ClipData mClipData;

    private static final String LABEL_PLAIN_TEXT_COPY = "LABEL_PLAIN_TEXT_COPY";

    private EditText mEditText;
    private TextView mTextView;

    private boolean dataIsCut = false;

    private Main2Activity addItemMenu(ContextMenu menu, int groupId, int itemId, int order, CharSequence title) {
        menu.add(groupId, itemId, order, title);
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        requestPermissions();
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        onPrimaryClipChangedListener = new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {}
        };

        findViewById(R.id.text).setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                addItemMenu(
                        menu
                    , ContextMenuItem.GROUP_ID
                    , ContextMenuItem.COPY.getId()
                    , 0
                    , ContextMenuItem.COPY.getName()
                ).addItemMenu(
                      menu
                    , ContextMenuItem.GROUP_ID
                    , ContextMenuItem.CUT.getId()
                    , 1
                    , ContextMenuItem.CUT.getName()
                );

                mTextView = (TextView) v;
            }
        });

        findViewById(R.id.image_view).setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                addItemMenu(
                    menu
                    ,ContextMenuItem.GROUP_ID
                    , ContextMenuItem.COPY.getId()
                    , 0
                    , ContextMenuItem.COPY.getName()
                ).addItemMenu(
                    menu
                    , ContextMenuItem.GROUP_ID
                    , ContextMenuItem.CUT.getId()
                    , 1
                    , ContextMenuItem.CUT.getName()
                );
            }
        });

        mEditText = findViewById(R.id.edit_text);
        mEditText.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                addItemMenu(
                        menu
                        , ContextMenuItem.GROUP_ID
                        , ContextMenuItem.COPY.getId()
                        , 0
                        , ContextMenuItem.COPY.getName()
                )
                .addItemMenu(
                        menu
                        , ContextMenuItem.GROUP_ID
                        , ContextMenuItem.CUT.getId()
                        , 1
                        , ContextMenuItem.CUT.getName()
                )
                .addItemMenu(
                      menu
                    , ContextMenuItem.GROUP_ID
                    , ContextMenuItem.PASTE.getId()
                    , 2
                    , ContextMenuItem.PASTE.getName()
                );
            }
        });
    }

    private void t(MenuItem item) {
        ContextMenu.ContextMenuInfo c = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo contextMenuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (contextMenuInfo != null) {
            // int pos = contextMenuInfo.position;
            TextView textView = (TextView) contextMenuInfo.targetView;
            if (textView != null) {
                Toast.makeText(this
                        , textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void copyData() {
        if (mTextView.getText() != null) {
            String mTextCopied = mTextView.getText().toString();
            mClipData = ClipData.newPlainText(LABEL_PLAIN_TEXT_COPY, mTextCopied);
            mClipboardManager.setPrimaryClip(mClipData);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getGroupId() == ContextMenuItem.GROUP_ID) {
            int n = item.getItemId();
            ContextMenuItem contextMenuItem = ContextMenuItem.get(n);
            if (contextMenuItem != null) {
                switch (contextMenuItem) {
                    case CUT:
                        copyData();
                        dataIsCut = true;
                        break;
                    case COPY:
                        copyData();
                        dataIsCut = false;
                        break;
                    case PASTE:
                        /**/
                        if (mClipboardManager.hasPrimaryClip()) {
                            ClipData clipData = mClipboardManager.getPrimaryClip();
                            //ClipDescription clipDescription = clipData.getDescription();
                            //if (clipDescription != null) { }
                            if (clipData != null && clipData.getItemCount() > 0) {
                                ClipData.Item itemClipData = clipData.getItemAt(0);
                                if (itemClipData != null) {
                                    Log.i("ITEM", itemClipData.getText().toString());
                                    mEditText.setText(itemClipData.getText());
                                    if (dataIsCut) {
                                        //mClipboardManager.setPrimaryClip(null);
                                        dataIsCut = false;
                                    }
                                }
                            }
                        }


                        break;
                }
            }
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mClipboardManager.addPrimaryClipChangedListener(onPrimaryClipChangedListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mClipboardManager.removePrimaryClipChangedListener(onPrimaryClipChangedListener);
    }
}
