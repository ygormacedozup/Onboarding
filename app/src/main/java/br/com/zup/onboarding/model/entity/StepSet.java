package br.com.zup.onboarding.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StepSet {
    @SerializedName("steps")
    @Expose
    private List<Step> steps;

    public List<Step> getSteps() {
        return steps;
    }
}