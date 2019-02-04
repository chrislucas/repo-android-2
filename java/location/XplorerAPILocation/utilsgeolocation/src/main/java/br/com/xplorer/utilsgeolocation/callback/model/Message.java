package br.com.xplorer.utilsgeolocation.callback.model;

/**
 * Created by r028367 on 19/03/2018.
 */

public abstract class Message {
    protected String stringMessage;

    public Message(String stringMessage) {
        this.stringMessage = stringMessage;
    }

    public Message() {
        stringMessage = "";
    }

    public String getStringMessage() {
        return stringMessage;
    }

    public void setStringMessage(String stringMessage) {
        this.stringMessage = stringMessage;
    }
}
