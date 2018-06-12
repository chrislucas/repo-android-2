package br.com.xplorer.packcustomviews;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by r028367 on 19/03/2018.
 */

public class UtilsAlertDialog {


    public class CustomAlertDialogBuilder extends AlertDialog.Builder {
        private CustomAlertDialogBuilder(Context context) {
            super(context);
        }

        private CustomAlertDialogBuilder(Context context, int themeResId) {
            super(context, themeResId);
        }


        private void setVisibilityProgressPercent(int visibility) {
            setFieldVisibility("mProgressPercent", visibility);
        }

        private void setFieldVisibility(String fieldName, int visibility) {
            try {
                Method method = TextView.class.getMethod("setVisibility", Integer.TYPE);
                Field[] fields = this.getClass().getSuperclass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.getName().equalsIgnoreCase(fieldName)) {
                        field.setAccessible(true);
                        TextView textView = (TextView) field.get(this);
                        method.invoke(textView, visibility);
                    }
                }
            } catch (Exception e) {}
        }
    }

    private CustomAlertDialogBuilder customAlertDialogBuilder;
    private AlertDialog alertDialog;
    private Handler handler;
    private Context context;

    public UtilsAlertDialog(Context context) {
        this.context = context;
        this.customAlertDialogBuilder = new CustomAlertDialogBuilder(context);
        this.alertDialog = customAlertDialogBuilder.create();
        this.handler = new Handler(Looper.getMainLooper());
    }

    public UtilsAlertDialog (Context context, Handler.Callback callback) {
        this.context = context;
        this.customAlertDialogBuilder = new CustomAlertDialogBuilder(context);
        this.alertDialog = customAlertDialogBuilder.create();
        this.handler = new Handler(callback);
    }

    public UtilsAlertDialog(Context context, @StyleRes int theme) {
        this.context = context;
        this.customAlertDialogBuilder = new CustomAlertDialogBuilder(context, theme);
        this.alertDialog = customAlertDialogBuilder.create();
        this.handler = new Handler(Looper.getMainLooper());
    }

    public UtilsAlertDialog (Context context,  @StyleRes int theme, Handler.Callback callback) {
        this.context = context;
        this.customAlertDialogBuilder = new CustomAlertDialogBuilder(context, theme);
        this.alertDialog = customAlertDialogBuilder.create();
        this.handler = new Handler(callback);
    }

    public CustomAlertDialogBuilder getCustomAlertDialogBuilder() {
        return customAlertDialogBuilder;
    }

    public void setPositiveButton(String positive, DialogInterface.OnClickListener onClickListener) {
        this.customAlertDialogBuilder.setPositiveButton(positive, onClickListener);
    }

    public void setNegativeButton(String negative, DialogInterface.OnClickListener onClickListener) {
        this.customAlertDialogBuilder.setNegativeButton(negative, onClickListener);
    }

    public void setNeutralButton(String neutral, DialogInterface.OnClickListener onClickListener) {
        this.customAlertDialogBuilder.setNeutralButton(neutral, onClickListener);
    }

    public UtilsAlertDialog buildDefault(boolean isCancelable, String title, String message) {
        customAlertDialogBuilder.setCancelable(isCancelable);
        customAlertDialogBuilder.setTitle(title);
        customAlertDialogBuilder.setMessage(message);
        return this;
    }

    public boolean isShowing() {
        return customAlertDialogBuilder.create().isShowing();
    }

    public CustomAlertDialogBuilder get() {
        return customAlertDialogBuilder;
    }

    public UtilsAlertDialog setMessage(String message) {
        this.customAlertDialogBuilder.setMessage(message);
        return this;
    }

    public UtilsAlertDialog setTitle(String title) {
        this.customAlertDialogBuilder.setTitle(title);
        return this;
    }


    public UtilsAlertDialog setVisibilityProgressPercent(int visibility) {
        this.customAlertDialogBuilder.setVisibilityProgressPercent(visibility);
        return this;
    }

    public UtilsAlertDialog setCancelMessage(Message message) {
        this.alertDialog.setCancelMessage(message);
        return this;
    }

    public UtilsAlertDialog setCancelableMessage(boolean canceledOnTouch) {
        this.alertDialog.setCanceledOnTouchOutside(canceledOnTouch);
        return this;
    }

    public UtilsAlertDialog setDismissMessage(Message message) {
        this.alertDialog.setDismissMessage(message);
        return this;
    }

    public interface OnDismissDialog {
        void onDismiss();
    }

    public void safeShowing() throws Exception {
        if(context instanceof Activity) {
            final Activity activity = (Activity) context;
            boolean isFinishing = activity.isFinishing();
            if(!isFinishing) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(!UtilsAlertDialog.this.alertDialog.isShowing()) {
                            UtilsAlertDialog.this.alertDialog.show();
                        }
                    }
                });
            }
        }
        else {
            throw new Exception("Context must be child of Activity");
        }
    }

    public void safeDismiss() throws Exception {
        if(context instanceof Activity) {
            final Activity activity = (Activity) context;
            if(!activity.isFinishing()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(UtilsAlertDialog.this.alertDialog.isShowing()) {
                            UtilsAlertDialog.this.alertDialog.dismiss();
                        }
                    }
                });
            }
        }
        else {
            throw new Exception("Context must be child of Activity");
        }
    }

    public void safeDismiss(final OnDismissDialog onDismissDialog) throws Exception {
        if(context instanceof Activity) {
            final Activity activity = (Activity) context;
            if(!activity.isFinishing()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(UtilsAlertDialog.this.alertDialog.isShowing()) {
                            UtilsAlertDialog.this.alertDialog.dismiss();
                            onDismissDialog.onDismiss();
                        }
                    }
                });
            }
        }
        else {
            throw new Exception("Context must be child of Activity");
        }
    }


}
