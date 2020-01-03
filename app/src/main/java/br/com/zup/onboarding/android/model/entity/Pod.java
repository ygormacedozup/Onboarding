package br.com.zup.onboarding.android.model.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pod implements Serializable {
    @SerializedName("namePod")
    private String namePod;

    public Pod(String name) {
        namePod = name;
    }

    public String getNamePod() {
        return namePod;
    }

    @NonNull
    @Override
    public String toString() {
        return "namePod=" + namePod;
    }
}