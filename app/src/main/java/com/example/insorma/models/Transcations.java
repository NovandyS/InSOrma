package com.example.insorma.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Transcations implements Parcelable {

    private Integer transID;
    private Integer userID;
    private Integer prodID;
    private String transDate;
    private Integer transQuant;
    private Integer transTotal;

    public void Transactions(Integer transID, Integer userID, Integer prodID, String transDate, Integer transQuant, Integer transTotal) {
        this.transID = transID;
        this.userID = userID;
        this.prodID = prodID;
        this.transDate = transDate;
        this.transQuant = transQuant;
        this.transTotal = transTotal;
    }

    public Transcations(){

    }

    protected Transcations(Parcel in) {
        transID = in.readInt();
        userID = in.readInt();
        prodID = in.readInt();
        transDate = in.readString();
        transQuant = in.readInt();
        transTotal = in.readInt();
    }

    public static final Creator<Transcations> CREATOR = new Creator<Transcations>() {
        @Override
        public Transcations createFromParcel(Parcel in) {
            return new Transcations(in);
        }

        @Override
        public Transcations[] newArray(int size) {
            return new Transcations[size];
        }
    };

    public Integer getTransID() {
        return transID;
    }

    public void setTransID(Integer transID) {
        this.transID = transID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getProdID() {
        return prodID;
    }

    public void setProdID(Integer prodID) {
        this.prodID = prodID;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public Integer getTransQuant() {
        return transQuant;
    }

    public void setTransQuant(Integer transQuant) {
        this.transQuant = transQuant;
    }

    public Integer getTransTotal() {
        return transTotal;
    }

    public void setTransTotal(Integer transTotal) {
        this.transTotal = transTotal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(transID);
        parcel.writeInt(userID);
        parcel.writeInt(prodID);
        parcel.writeString(transDate);
        parcel.writeInt(transQuant);
        parcel.writeInt(transTotal);
    }
}
