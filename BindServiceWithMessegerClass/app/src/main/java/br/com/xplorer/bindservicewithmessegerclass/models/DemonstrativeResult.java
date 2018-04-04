package br.com.xplorer.bindservicewithmessegerclass.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by r028367 on 03/04/2018.
 */

public class DemonstrativeResult implements Parcelable {

    private String code, description;
    private double reference
            , result // Se positivo é um valor de "VENCIMENTO" senão é um "DESCONTO"
            ;

    public DemonstrativeResult() {}

    private DemonstrativeResult(Parcel reader) {
        readerParcel(reader);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel writer, int flags) {
        writer.writeString(code);
        writer.writeString(description);
        writer.writeDouble(reference);
        writer.writeDouble(result);
    }

    private void readerParcel(Parcel reader) {
        code = reader.readString();
        description = reader.readString();
        reference = reader.readDouble();
        result = reader.readDouble();
    }

    public String getFormattedReference() {
        return Demonstrative.realDecimalFormat.format(reference);
    }

    public String getFormattedResult() {
        String f = Demonstrative.realDecimalFormat.format(result);
        return result > 0 ? "+".concat(f) : f ;
    }

    public static final Parcelable.Creator<DemonstrativeResult> CREATOR = new Parcelable.Creator<DemonstrativeResult>() {
        @Override
        public DemonstrativeResult createFromParcel(Parcel source) {
            return new DemonstrativeResult(source);
        }

        @Override
        public DemonstrativeResult[] newArray(int size) {
            return new DemonstrativeResult[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getReference() {
        return reference;
    }

    public void setReference(double reference) {
        this.reference = reference;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
