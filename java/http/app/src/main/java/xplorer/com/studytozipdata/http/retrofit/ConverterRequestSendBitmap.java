package xplorer.com.studytozipdata.http.retrofit;

import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Converter;
import xplorer.com.studytozipdata.CompactImage;

public class ConverterRequestSendBitmap implements Converter<CompactImage, RequestBody> {

    @Override
    public RequestBody convert(@NonNull CompactImage value) throws IOException {
        byte [] data = new ConverterSendBitmap().fromObjectToJson(value).getBytes();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gzipOutputStream.write(data);
        gzipOutputStream.close();
        RequestBody requestBody = RequestBody
                .create(MediaType.parse("application/json; charset=utf-8")
                , byteArrayOutputStream.toByteArray());
        Request request = new Request.Builder()
                .get()
                .header("Content-Encoding", "gzip").build();
        return request.body();
    }
}
