package br.com.zup.onboarding.android.model.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ZupperExists {
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("exists")
    private boolean exists;

    @SerializedName("token")
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean exists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @NonNull
    @Override
    public String toString() {
        return "name=" + name + " email=" + email + " exists=" + exists + " token=" + token;
    }
}
