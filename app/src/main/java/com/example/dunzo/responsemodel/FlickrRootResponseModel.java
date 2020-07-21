package com.example.dunzo.responsemodel;

import com.google.gson.annotations.SerializedName;

public class FlickrRootResponseModel {
    @SerializedName("photos")
    private PhotosResponseModel mPhotosResponseModel;

    public PhotosResponseModel getPhotosResponseModel() {
        return mPhotosResponseModel;
    }

// --Commented out by Inspection START (21/7/20 9:27 PM):
//    public void setPhotosResponseModel(PhotosResponseModel photosResponseModel) {
//        this.mPhotosResponseModel = photosResponseModel;
//    }
// --Commented out by Inspection STOP (21/7/20 9:27 PM)
}
