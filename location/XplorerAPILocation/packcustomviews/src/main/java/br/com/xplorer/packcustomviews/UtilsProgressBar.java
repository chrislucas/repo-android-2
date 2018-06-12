package br.com.xplorer.packcustomviews;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;

import java.util.List;

/**
 * Created by r028367 on 19/03/2018.
 */

public class UtilsProgressBar {

    public class CustomProgressDialog extends AlertDialog {

        public CustomProgressDialog(Context context) {
            super(context);
        }

        public CustomProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }

        public CustomProgressDialog(Context context, int themeResId) {
            super(context, themeResId);
        }

        @Override
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

        }
    }


    private CustomProgressDialog customProgressDialog;

    public UtilsProgressBar(CustomProgressDialog customProgressDialog) {
        this.customProgressDialog = customProgressDialog;

    }
}
