package br.com.zup.onboarding.android.model.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Step implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("stepName")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("duration")
    private String duration;

    @SerializedName("question")
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