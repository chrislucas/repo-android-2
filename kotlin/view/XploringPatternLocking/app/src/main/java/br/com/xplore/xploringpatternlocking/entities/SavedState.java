package br.com.xplore.xploringpatternlocking.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * Created by C_Luc on 14/01/2018.
 */

public class SavedState extends View.BaseSavedState {
    private final String mSerializedPattern;
    private final int mDisplayMode;
    private final boolean mInputEnabled;
    private final boolean mInStealthMode;
    private final boolean mTactileFeedbackEnabled;


    public SavedState(Parcel reader) {
        super(reader);
        mSerializedPattern = reader.readString();
        mDisplayMode = (int) reader.readValue(Integer.class.getClassLoader());
        mInputEnabled = (boolean) reader.readValue(Boolean.class.getClassLoader());
        mInStealthMode = (boolean) reader.readValue(Boolean.class.getClassLoader());
        mTactileFeedbackEnabled = (boolean) reader.readValue(Boolean.class.getClassLoader());
    }

    public SavedState(Parcelable superState, String mSerializedPattern, int mDisplayMode, boolean mInputEnabled, boolean mInStealthMode, boolean mTactileFeedbackEnabled) {
        super(superState);
        this.mSerializedPattern = mSerializedPattern;
        this.mDisplayMode = mDisplayMode;
        this.mInputEnabled = mInputEnabled;
        this.mInStealthMode = mInStealthMode;
        this.mTactileFeedbackEnabled = mTactileFeedbackEnabled;
    }

    @Override
    public void writeToParcel(Parcel writer, int flags) {
        super.writeToParcel(writer, flags);
        writer.writeString(mSerializedPattern);
        writer.writeValue(mDisplayMode);
        writer.writeValue(mInputEnabled);
        writer.writeValue(mInStealthMode);
        writer.writeValue(mTactileFeedbackEnabled);
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }


    public String getmSerializedPattern() {
        return mSerializedPattern;
    }

    public int getmDisplayMode() {
        return mDisplayMode;
    }

    public boolean ismInputEnabled() {
        return mInputEnabled;
    }

    public boolean ismInStealthMode() {
        return mInStealthMode;
    }

    public boolean ismTactileFeedbackEnabled() {
        return mTactileFeedbackEnabled;
    }

    public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
        @Override
        public SavedState createFromParcel(Parcel parcel) {
            return new SavedState(parcel);
        }

        @Override
        public SavedState[] newArray(int size) {
            return new SavedState[size];
        }
    }
}
