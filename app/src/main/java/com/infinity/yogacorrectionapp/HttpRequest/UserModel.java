package com.infinity.yogacorrectionapp.HttpRequest;

public class UserModel {

    private String capture;
    private int level;
    private String token;

    public UserModel(String capture, int level, String token) {
        this.capture = capture;
        this.level = level;
        this.token = token;
    }

    public void setCapture(String capture) {
        this.capture = capture;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCapture() {
        return capture;
    }

    public int getLevel() {
        return level;
    }

    public String getToken() {
        return token;
    }
}
