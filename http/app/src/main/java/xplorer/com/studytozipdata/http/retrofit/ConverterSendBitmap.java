package xplorer.com.studytozipdata.http.retrofit;

import org.json.JSONException;
import org.json.JSONObject;

import xplorer.com.studytozipdata.CompactImage;
import xplorer.com.studytozipdata.http.MessageResponse;

public class ConverterSendBitmap {

    public MessageResponse fromJsonToObject(String json) {
        return new MessageResponse(json);
    }

    public String fromObjectToJson(CompactImage compactImage) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("image", compactImage.getEncodedData());
        }
        catch (JSONException e) {

        }
        return jsonObject.toString();
    }
}
