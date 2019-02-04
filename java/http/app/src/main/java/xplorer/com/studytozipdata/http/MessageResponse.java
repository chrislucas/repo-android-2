package xplorer.com.studytozipdata.http;

import vc.icomon.com.sretrofit.IResponse;

public class MessageResponse implements IResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
