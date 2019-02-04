package xplorer.com.studytozipdata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.constraint.solver.widgets.Helper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import java.util.List;

import vc.icomon.com.sretrofit.CallbackRequestAPI2;
import xplorer.com.studytozipdata.Helpers.HelperBitmap;
import xplorer.com.studytozipdata.http.MessageResponse;
import xplorer.com.studytozipdata.http.SenderImage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BitmapFactory.Options options = new BitmapFactory.Options();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wp3, options);

        final byte [] buffer = HelperBitmap.resizeImageAndChangeQualityFromByteArray(
                HelperBitmap.fromBitmapToByteArray(bitmap, Bitmap.CompressFormat.PNG, 80)
                , 1024
                , 80
                , HelperBitmap.Dimension2D.HEIGHT
        );
        final CallbackRequestAPI2<MessageResponse> callback = new CallbackRequestAPI2<MessageResponse>() {
            @Override
            public void onSuccessRequest(MessageResponse data) {
                showMessage(data.getMessage());
            }

            @Override
            public void onSuccessRequest(List<MessageResponse> data) {}

            @Override
            public void onnFailureRequest(MessageResponse response) {
                showMessage(response.getMessage());
            }
        };

         new Handler().post(new Runnable() {
            @Override
            public void run() {
                String encoded = Base64.encodeToString(buffer, Base64.DEFAULT);
                CompactImage compactImage = new CompactImage(encoded);
                new SenderImage().send(compactImage
                        , "http://172.28.10.148:3000/", callback);
            }
        });



    }

    private void showMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }
}
