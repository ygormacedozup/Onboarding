package br.com.zup.onboarding.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alternative {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("correct")
    @Expose
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
}
