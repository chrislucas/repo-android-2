package br.com.xplorer.bindservicewithmessegerclass.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by r028367 on 04/04/2018.
 */

public class User implements Parcelable {

    private String name, register;

    public User(String name, String register) {
        this.name = name;
        this.register = register;
    }

    private User(Parcel reader) {
        readerParcel(reader);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel writer, int flags) {
         writer.writeString(this.name);
         writer.writeString(this.register);
    }

    private void readerParcel(Parcel reader) {
        this.name = reader.readString();
        this.register = reader.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
