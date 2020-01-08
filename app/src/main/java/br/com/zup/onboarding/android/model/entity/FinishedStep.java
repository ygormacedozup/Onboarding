package br.com.zup.onboarding.android.model.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FinishedStep {
    @SerializedName("id")
    private Object id;

    @SerializedName("scoreBoard")
    private int scoreBoard;

    @SerializedName("licensed")
    private boolean isLicensed;

    @SerializedName("zupper")
    private User zupper;

    @SerializedName("question")
    private List<Question> questions;

    @SerializedName("percentageScore")
    private double percentageScore;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public int getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(int scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public boolean isLicensed() {
        return isLicensed;
    }

    public void setLicensed(boolean licensed) {
        isLicensed = licensed;
    }


    public Object getZupper() {
        return zupper;
    }

    public void setZupper(User zupper) {
        this.zupper = zupper;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public double getPercentageScore() {
        return percentageScore;
    }

    public void setPercentageScore(double percentageScore) {
        this.percentageScore = percentageScore;
    }

    @NonNull
    @Override
    public String toString() {
        return "id=" + id + " scoreBoard=" + scoreBoard + " isLicensed=" + isLicensed
                     + " zupper=" + zupper + " question=" + questions
                     + " percentageScore=" + percentageScore;
    }
}
