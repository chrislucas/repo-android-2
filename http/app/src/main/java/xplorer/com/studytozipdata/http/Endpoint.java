package xplorer.com.studytozipdata.http;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import xplorer.com.studytozipdata.CompactImage;

public interface Endpoint {

    @GET("/send_zip_data")
    Call<MessageResponse> sendZippedData(@Body CompactImage compactImage);
}
