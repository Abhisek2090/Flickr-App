package com.example.dunzo.responsemodel;

import com.google.gson.annotations.SerializedName;

public class PhotoResponseModel {
    @SerializedName("id")
    private String id;
    @SerializedName("secret")
    private String secret;
    @SerializedName("server")
    private String server;
    @SerializedName("farm")
    private Integer farm;
    @SerializedName("title")
    private String title;


    public String getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }


    public Integer getFarm() {
        return farm;
    }

    public String getTitle() {
        return title;
    }
}
