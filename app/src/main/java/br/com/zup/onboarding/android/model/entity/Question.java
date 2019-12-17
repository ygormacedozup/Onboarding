package br.com.zup.onboarding.android.model.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
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

    @NonNull
    @Override
    public String toString() {
        return "description=" + description + "\n" + "alternatives=" + alternatives;
    }
}