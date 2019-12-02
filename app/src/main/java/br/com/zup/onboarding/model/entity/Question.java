package br.com.zup.onboarding.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("description")
    private String description;

    @SerializedName("alternatives")
    private List<Alternative> alternatives;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Alternative> getAlternatives() {
        return alternatives;
    }
}