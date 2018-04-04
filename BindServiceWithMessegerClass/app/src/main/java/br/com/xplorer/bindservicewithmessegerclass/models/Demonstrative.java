package br.com.xplorer.bindservicewithmessegerclass.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by r028367 on 03/04/2018.
 */

public class Demonstrative implements Parcelable {

    private double discountedSalary
            , baseSalary                // salario base
            , contributionSalaryINSS    // valor em reais da contribuição no INSS
            , fgtsBaseCalc              // valor base do calculo do FGTS
            , fgtsTaxMouth              // contribuição mensal de FGTS
            , irffBaseCalc              // Base para o calculo do IRFF
            , totalSalary               // somatorio dos vencimentos
            , totalDiscounts            // somatorio dos descontos
            ;
    private String irff;
    private long dataDemonstrative;
    private List<DemonstrativeResult>  results;


    public static final DecimalFormat realDecimalFormat = new DecimalFormat("###,###.00");

    public Demonstrative() {
        this.results = new ArrayList<>();
    }

    private Demonstrative(Parcel in) {
        readerParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel writer, int flags) {
        writer.writeDouble(discountedSalary);
        writer.writeDouble(baseSalary);
        writer.writeDouble(contributionSalaryINSS);
        writer.writeDouble(fgtsBaseCalc);
        writer.writeDouble(fgtsTaxMouth);
        writer.writeDouble(irffBaseCalc);
        writer.writeDouble(totalSalary);
        writer.writeDouble(totalDiscounts);
        writer.writeString(irff);
        writer.writeLong(dataDemonstrative);
        if(this.results == null)
            this.results = new ArrayList<>();
        writer.writeList(results);
    }

    private void readerParcel(Parcel reader) {
        this.discountedSalary = reader.readDouble();
        this.baseSalary = reader.readDouble();
        this.contributionSalaryINSS = reader.readDouble();
        this.fgtsBaseCalc = reader.readDouble();
        this.fgtsTaxMouth = reader.readDouble();
        this.irffBaseCalc = reader.readDouble();
        this.totalSalary = reader.readDouble();
        this.totalDiscounts = reader.readDouble();
        this.irff = reader.readString();
        this.dataDemonstrative = reader.readLong();
        reader.readList(this.results, DemonstrativeResult.class.getClassLoader());
    }

    public static final Parcelable.Creator<Demonstrative> CREATOR = new Parcelable.Creator<Demonstrative>() {
        @Override
        public Demonstrative createFromParcel(Parcel source) {
            return new Demonstrative(source);
        }

        @Override
        public Demonstrative[] newArray(int size) {
            return new Demonstrative[size];
        }
    };

    public String getFormattedDiscountedSalary() {
        return realDecimalFormat.format(discountedSalary);
    }

    public void setDiscountedSalary(double discountedSalary) {
        this.discountedSalary = discountedSalary;
    }

    public String getFormattedBaseSalary() {
        return realDecimalFormat.format(baseSalary);
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getFormattedContributionSalaryINSS() {
        return realDecimalFormat.format(contributionSalaryINSS);
    }

    public void setContributionSalaryINSS(double contributionSalaryINSS) {
        this.contributionSalaryINSS = contributionSalaryINSS;
    }

    public String getFormattedFgtsBaseCalc() {
        return realDecimalFormat.format(fgtsBaseCalc);
    }

    public void setFgtsBaseCalc(double fgtsBaseCalc) {
        this.fgtsBaseCalc = fgtsBaseCalc;
    }

    public String getFormattedIrffBaseCalc() {
        return realDecimalFormat.format(irffBaseCalc);
    }

    public void setIrffBaseCalc(double irffBaseCalc) {
        this.irffBaseCalc = irffBaseCalc;
    }

    public String getIrff() {
        return irff;
    }

    public void setIrff(String irff) {
        this.irff = irff;
    }

    public long getDataDemonstrative() {
        return dataDemonstrative;
    }

    public void setDataDemonstrative(long dataDemonstrative) {
        this.dataDemonstrative = dataDemonstrative;
    }

    public List<DemonstrativeResult> getResults() {
        return results;
    }

    public void setResults(List<DemonstrativeResult> results) {
        this.results = results;
    }

    public String getFormattedTotalSalary() {
        return realDecimalFormat.format(totalSalary);
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public String getFormattedTotalDiscounts() {
        return realDecimalFormat.format(totalDiscounts);
    }

    public void setTotalDiscounts(double totalDiscounts) {
        this.totalDiscounts = totalDiscounts;
    }

    public String getFormattedFgtsTaxMouth() {
        return realDecimalFormat.format(fgtsTaxMouth);
    }

    public void setFgtsTaxMouth(double fgtsTaxMouth) {
        this.fgtsTaxMouth = fgtsTaxMouth;
    }
}
