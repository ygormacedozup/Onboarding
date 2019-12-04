package br.com.zup.onboarding.model.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Step {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("stepName")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("questions")
    @Expose
    private List<Question> questions;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @NonNull
    @Override
    public String toString() {
        return "questions=\n" + questions;
    }
}