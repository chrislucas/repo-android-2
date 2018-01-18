package xplore.com.br.xplorerscreenoverlay;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import xplore.com.br.xplorerscreenoverlay.services.OverlayService;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMS = 10;
    private static final int REQUEST_CODE_PERM_SYS_ALERT_WINDOW = 11;

    private static final  String [] permissions = {
         Manifest.permission.SYSTEM_ALERT_WINDOW
    };

    private void askForAccessPermissions() {
        ArrayList<String> permissionsDenied = new ArrayList<>();
        for(String permission : permissions) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
               Log.i("SHOULD_SHOW_RQ_PERM", permission);
            }
            if(ContextCompat.checkSelfPermission(this
                    , permission) == PackageManager.PERMISSION_DENIED) {
                permissionsDenied.add(permission);
            }
        }
        if(permissionsDenied.size() >  0) {
            String [] p = permissionsDenied.toArray(new String[permissionsDenied.size()]);
            ActivityCompat.requestPermissions(this
                    , p
                    , REQUEST_CODE_PERMS);
        }
        /**
         * Permissao para desenhar telas que se sobrepoem a outras telas sao necessarias
         * a partir da api 23
         * */

        if(verifyCanDrawOverlays()) {
            startOverlayService();
        }
        else {
            // pedir permissao para Manifest.permission.SYSTEM_ALERT_WINDOW do jeito tradicional nao funciona
            /**
             * Se eu nao estiver enganado essa permissao vai marcar "sim" no atributo "Sobrepor a outros Apps"
             * Esse atributo pode ser visto quando acessamos um app dentro do menu de CONFIGURACOES
             * */
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse(String.format("package:%s",getPackageName())));
            startActivityForResult(intent, REQUEST_CODE_PERM_SYS_ALERT_WINDOW);
        }
    }

    private void startOverlayService() {
        Intent intent = new Intent(this, OverlayService.class);
        startService(intent);
        finish();
    }

    private boolean verifyCanDrawOverlays() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return Settings.canDrawOverlays(this);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_PERM_SYS_ALERT_WINDOW:
                if(verifyCanDrawOverlays())
                    startOverlayService();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions
            , @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMS:
            case REQUEST_CODE_PERM_SYS_ALERT_WINDOW:
                for(int i=0; i<grantResults.length; i++) {
                    if(grantResults[i] == PermissionChecker.PERMISSION_GRANTED
                            && permissions[i].equals(Manifest.permission.SYSTEM_ALERT_WINDOW)) {
                        startOverlayService();
                        break;
                    }
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askForAccessPermissions();
    }
}
