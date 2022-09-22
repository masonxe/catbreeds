package com.pragma.android_java.models;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class Cat implements Parcelable {

    public CatWeight weight;
    public CatImage image;
    public String id;
    public String name;

    @SerializedName("cfa_url")
    public String cfaUrl;
    @SerializedName("vetstreet_url")
    public String vetstreetUrl;
    @SerializedName("vcahospitals_url")
    public String vcahospitalsUrl;

    public String temperament;
    public String origin;

    @SerializedName("country_codes")
    public String countryCodes;
    @SerializedName("country_code")
    public String countryCode;
    public String description;

    @SerializedName("life_span")
    public String lifeSpan;

    public int indoor;
    public int lap;

    @SerializedName("alt_names")
    public String altNames;

    public int adaptability;

    @SerializedName("affection_level")
    public int affectionLevel;

    @SerializedName("child_friendly")
    public int childFriendly;

    @SerializedName("dog_friendly")
    public int dogFriendly;

    @SerializedName("energy_level")
    public int energyLevel;

    public int grooming;

    @SerializedName("health_issues")
    public int healthIssues;

    public int intelligence;

    @SerializedName("shedding_level")
    public int sheddingLevel;

    @SerializedName("social_needs")
    public int socialNeeds;

    @SerializedName("stranger_friendly")
    public int strangerFriendly;

    public int vocalisation;
    public int experimental;
    public int hairless;
    public int natural;
    public int rare;
    public int rex;

    @SerializedName("suppressed_tail")
    public int suppressedTail;

    @SerializedName("short_legs")
    public int shortLegs;

    @SerializedName("wikipediaUrl")
    public String wikipedia_url;

    public int hypoallergenic;

    @SerializedName("referenceImageId")
    public String reference_image_id;


    protected Cat(Parcel in) {
        weight = in.readParcelable(CatWeight.class.getClassLoader());
        image = in.readParcelable(CatImage.class.getClassLoader());
        id = in.readString();
        name = in.readString();
        cfaUrl = in.readString();
        vetstreetUrl = in.readString();
        vcahospitalsUrl = in.readString();
        temperament = in.readString();
        origin = in.readString();
        countryCodes = in.readString();
        countryCode = in.readString();
        description = in.readString();
        lifeSpan = in.readString();
        indoor = in.readInt();
        lap = in.readInt();
        altNames = in.readString();
        adaptability = in.readInt();
        affectionLevel = in.readInt();
        childFriendly = in.readInt();
        dogFriendly = in.readInt();
        energyLevel = in.readInt();
        grooming = in.readInt();
        healthIssues = in.readInt();
        intelligence = in.readInt();
        sheddingLevel = in.readInt();
        socialNeeds = in.readInt();
        strangerFriendly = in.readInt();
        vocalisation = in.readInt();
        experimental = in.readInt();
        hairless = in.readInt();
        natural = in.readInt();
        rare = in.readInt();
        rex = in.readInt();
        suppressedTail = in.readInt();
        shortLegs = in.readInt();
        wikipedia_url = in.readString();
        hypoallergenic = in.readInt();
        reference_image_id = in.readString();
    }

    public static final Creator<Cat> CREATOR = new Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel in) {
            return new Cat(in);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(weight, i);
        parcel.writeParcelable(image, i);
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(cfaUrl);
        parcel.writeString(vetstreetUrl);
        parcel.writeString(vcahospitalsUrl);
        parcel.writeString(temperament);
        parcel.writeString(origin);
        parcel.writeString(countryCodes);
        parcel.writeString(countryCode);
        parcel.writeString(description);
        parcel.writeString(lifeSpan);
        parcel.writeInt(indoor);
        parcel.writeInt(lap);
        parcel.writeString(altNames);
        parcel.writeInt(adaptability);
        parcel.writeInt(affectionLevel);
        parcel.writeInt(childFriendly);
        parcel.writeInt(dogFriendly);
        parcel.writeInt(energyLevel);
        parcel.writeInt(grooming);
        parcel.writeInt(healthIssues);
        parcel.writeInt(intelligence);
        parcel.writeInt(sheddingLevel);
        parcel.writeInt(socialNeeds);
        parcel.writeInt(strangerFriendly);
        parcel.writeInt(vocalisation);
        parcel.writeInt(experimental);
        parcel.writeInt(hairless);
        parcel.writeInt(natural);
        parcel.writeInt(rare);
        parcel.writeInt(rex);
        parcel.writeInt(suppressedTail);
        parcel.writeInt(shortLegs);
        parcel.writeString(wikipedia_url);
        parcel.writeInt(hypoallergenic);
        parcel.writeString(reference_image_id);
    }
}
