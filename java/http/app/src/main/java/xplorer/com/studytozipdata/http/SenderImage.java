package xplorer.com.studytozipdata.http;

import android.support.annotation.NonNull;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vc.icomon.com.sretrofit.CallbackRequestAPI2;
import vc.icomon.com.sretrofit.RetrofitServiceGenerator;
import xplorer.com.studytozipdata.CompactImage;
import xplorer.com.studytozipdata.http.retrofit.ConverterFactoryBitmapMessage;

public class SenderImage {

    public void send(CompactImage compactImage, String url
            , final CallbackRequestAPI2<MessageResponse> callbackRequestAPI2) {

        Endpoint endpoint = RetrofitServiceGenerator.getService(Endpoint.class
                , new ConverterFactoryBitmapMessage(), url);
        Call<MessageResponse> call = endpoint.sendZippedData(compactImage);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(@NonNull Call<MessageResponse> call
                    , @NonNull Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    callbackRequestAPI2.onSuccessRequest(response.body());
                }
                else {
                    String message = String.format(Locale.getDefault(), "Problemas, codigo %d", response.code());
                    callbackRequestAPI2.onnFailureRequest(new MessageResponse(message));
                }
            }

            @Override
            public void onFailure(@NonNull Call<MessageResponse> call, @NonNull Throwable t) {
                callbackRequestAPI2.onnFailureRequest(new MessageResponse("Erro"));
            }
        });
    }

}
