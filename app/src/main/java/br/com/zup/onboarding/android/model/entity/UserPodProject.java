package br.com.zup.onboarding.android.model.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserPodProject implements Serializable {

    @SerializedName("podUser")
    private String podUser;
    @SerializedName("projectUser")
    private String projectUser;
    @SerializedName("locatedUser")
    private String locatedUser;

    public String getPodUser() {
        return podUser;
    }

    public void setPodUser(String podUser) {
        this.podUser = podUser;
    }

    public String getProjectUser() {
        return projectUser;
    }

    public void setProjectUser(String projectUser) {
        this.projectUser = projectUser;
    }

    public String getLocatedUser() {
        return locatedUser;
    }

    public void setLocatedUser(String locatedUser) {
        this.locatedUser = locatedUser;
    }

    @NonNull
    @Override
    public String toString() {
        return "podUser=" + podUser + " projectUser=" + projectUser + " locatedUser=" + locatedUser;
    }
}
