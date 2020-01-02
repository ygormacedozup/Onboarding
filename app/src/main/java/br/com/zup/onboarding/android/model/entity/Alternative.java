package br.com.zup.onboarding.android.model.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Alternative implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("description")
    private String description;

    @SerializedName("correct")
    private Boolean isCorrect;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isCorrect() {
        return isCorrect;
    }

    @NonNull
    @Override
    public String toString() {
        return "\ndescription=" + description + "\n" + "isCorrect=" + isCorrect;
    }
}
