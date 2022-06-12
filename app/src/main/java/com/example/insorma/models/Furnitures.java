package com.example.insorma.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Furnitures implements Parcelable{

    private String furnitureImage;
    private String furnitureName;
    private Double furnitureRating;
    private Integer furniturePrice;
    private String furnitureDesc;

    public Furnitures(String furnitureImage, String furnitureName, Double furnitureRating, Integer furniturePrice, String furnitureDesc) {
        this.furnitureImage = furnitureImage;
        this.furnitureName = furnitureName;
        this.furnitureRating = furnitureRating;
        this.furniturePrice = furniturePrice;
        this.furnitureDesc = furnitureDesc;
    }

    public Furnitures(){

    }

    protected Furnitures(Parcel in) {
        furnitureImage = in.readString();
        furnitureName = in.readString();
        furnitureRating = in.readDouble();
        furniturePrice = in.readInt();
        furnitureDesc = in.readString();
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

    public String getFurnitureImage() {
        return furnitureImage;
    }

    public void setFurnitureImage(String furnitureImage) {
        this.furnitureImage = furnitureImage;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public Double getFurnitureRating() {
        return furnitureRating;
    }

    public void setFurnitureRating(Double furnitureRating) {
        this.furnitureRating = furnitureRating;
    }

    public Integer getFurniturePrice() {
        return furniturePrice;
    }

    public void setFurniturePrice(Integer furniturePrice) {
        this.furniturePrice = furniturePrice;
    }

    public String getFurnitureDesc() {
        return furnitureDesc;
    }

    public void setFurnitureDesc(String furnitureDesc) {
        this.furnitureDesc = furnitureDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(furnitureImage);
        parcel.writeString(furnitureName);
        parcel.writeDouble(furnitureRating);
        parcel.writeInt(furniturePrice);
        parcel.writeString(furnitureDesc);
    }
}
