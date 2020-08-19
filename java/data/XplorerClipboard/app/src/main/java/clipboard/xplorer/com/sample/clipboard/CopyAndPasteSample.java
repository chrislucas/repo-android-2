package clipboard.xplorer.com.sample.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import clipboard.xplorer.com.sample.clipboard.R;
import clipboard.xplorer.com.sample.phonestate.ContextMenuItem;

public class CopyAndPasteSample extends AppCompatActivity {







    private ClipboardManager.OnPrimaryClipChangedListener onPrimaryClipChangedListener;

    private ClipboardManager mClipboardManager;

    private ClipData mClipData;

    private static final String LABEL_PLAIN_TEXT_COPY = "LABEL_PLAIN_TEXT_COPY";

    private EditText mEditText;
    private TextView mTextView;

    private boolean dataIsCut = false;

    private CopyAndPasteSample addItemMenu(ContextMenu menu, int groupId, int itemId, int order, CharSequence title) {
        menu.add(groupId, itemId, order, title);
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
                        paste();
                        break;
                }
            }
        }
        return true;
    }

    private void paste() {
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
