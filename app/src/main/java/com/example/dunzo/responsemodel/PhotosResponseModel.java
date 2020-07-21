package com.example.dunzo.responsemodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotosResponseModel {
    @SerializedName("page")
    private Integer page;
    @SerializedName("pages")
    private Integer pages;
    @SerializedName("perpage")
    private Integer perpage;
    @SerializedName("total")
    private String total;
    @SerializedName("photo")
    private List<PhotoResponseModel> mPhotoResponseModel = null;


    public Integer getPages() {
        return pages;
    }

    public List<PhotoResponseModel> getPhotoResponseModel() {
        return mPhotoResponseModel;
    }

    public void setPhotoResponseModel(List<PhotoResponseModel> photoResponseModel) {
        this.mPhotoResponseModel = photoResponseModel;
    }
}
