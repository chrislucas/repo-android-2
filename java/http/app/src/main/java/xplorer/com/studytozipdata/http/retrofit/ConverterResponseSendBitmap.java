package xplorer.com.studytozipdata.http.retrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import xplorer.com.studytozipdata.http.MessageResponse;

public class ConverterResponseSendBitmap implements Converter<ResponseBody, MessageResponse> {

    @Override
    public MessageResponse convert(ResponseBody value) throws IOException {
        return new ConverterSendBitmap().fromJsonToObject(value.string());
    }
}
