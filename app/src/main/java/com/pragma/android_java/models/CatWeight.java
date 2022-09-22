package com.pragma.android_java.models;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class CatWeight implements Parcelable {

    public String imperial;
    public String metric;

    protected CatWeight(Parcel in) {
        imperial = in.readString();
        metric = in.readString();
    }

    public static final Creator<CatWeight> CREATOR = new Creator<CatWeight>() {
        @Override
        public CatWeight createFromParcel(Parcel in) {
            return new CatWeight(in);
        }

        @Override
        public CatWeight[] newArray(int size) {
            return new CatWeight[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(imperial);
        parcel.writeString(metric);
    }
}
