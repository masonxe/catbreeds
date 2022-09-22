package com.pragma.android_java.models;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class CatImage implements Parcelable {

    public String id;
    public int width;
    public int height;
    public String url;

    public CatImage(String id, int width, int height, String url) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.url = url;
    }

    protected CatImage(Parcel in) {
        id = in.readString();
        width = in.readInt();
        height = in.readInt();
        url = in.readString();
    }

    public static final Creator<CatImage> CREATOR = new Creator<CatImage>() {
        @Override
        public CatImage createFromParcel(Parcel in) {
            return new CatImage(in);
        }

        @Override
        public CatImage[] newArray(int size) {
            return new CatImage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeInt(width);
        parcel.writeInt(height);
        parcel.writeString(url);
    }
}
