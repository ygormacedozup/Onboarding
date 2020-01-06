package br.com.zup.onboarding.android.model.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {
    @SerializedName("nameLocation")
    private String nameLocation;

    public Location(String name) {
        nameLocation = name;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    @NonNull
    @Override
    public String toString() {
        return "nameLocation=" + nameLocation;
    }
}