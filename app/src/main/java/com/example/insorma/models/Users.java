package com.example.insorma.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable{
    private Integer userID;
    private String userEmail;
    private String userUName;
    private String userPass;
    private String userPhone;

    public Users(String userEmail, String userUName, String userPass, String userPhone) {
        this.userEmail = userEmail;
        this.userUName = userUName;
        this.userPass = userPass;
        this.userPhone = userPhone;
    }

    public Users(){

    }

    protected Users(Parcel in) {
        userID = in.readInt();
        userEmail = in.readString();
        userUName = in.readString();
        userPass = in.readString();
        userPhone = in.readString();
    }

    public static final Parcelable.Creator<Users> CREATOR = new Parcelable.Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserUName() {
        return userUName;
    }

    public void setUserUName(String userUName) {
        this.userUName = userUName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userID);
        parcel.writeString(userEmail);
        parcel.writeString(userUName);
        parcel.writeString(userPass);
        parcel.writeString(userPhone);
    }

}
