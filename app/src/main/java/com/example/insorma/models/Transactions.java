package com.example.insorma.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Transactions implements Parcelable {

    private Integer transID;
    private Integer userID;
    private String prodID;
    private String transDate;
    private Integer transQuant;

    public Transactions(Integer transID, Integer userID, String prodID, String transDate, Integer transQuant){
        this.transID = transID;
        this.userID = userID;
        this.prodID = prodID;
        this.transDate = transDate;
        this.transQuant = transQuant;
    }

    public Transactions(){

    }

    public Transactions(Integer userID, String prodID, String transDate, Integer transQuant){
        this.userID = userID;
        this.prodID = prodID;
        this.transDate = transDate;
        this.transQuant = transQuant;
    }

    protected Transactions(Parcel in) {
        transID = in.readInt();
        userID = in.readInt();
        prodID = in.readString();
        transDate = in.readString();
        transQuant = in.readInt();
    }

    public static final Creator<Transactions> CREATOR = new Creator<Transactions>() {
        @Override
        public Transactions createFromParcel(Parcel in) {
            return new Transactions(in);
        }

        @Override
        public Transactions[] newArray(int size) {
            return new Transactions[size];
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

    public String getProdID() {
        return prodID;
    }

    public void setProdID(String prodID) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(transID);
        parcel.writeInt(userID);
        parcel.writeString(prodID);
        parcel.writeString(transDate);
        parcel.writeInt(transQuant);
    }
}
