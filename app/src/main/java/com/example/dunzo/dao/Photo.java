package com.example.dunzo.dao;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = {@Index(value = "PhotoId", unique = true)})
public class Photo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "AutoId")
    private int autoId;

    @ColumnInfo(name = "PhotoId")
    private String photoId;

    @ColumnInfo(name = "PhotoUrl")
    private String photoUrl;

    public Photo(String photoId, String photoUrl) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
    }

    public String getPhotoId() {
        return photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

// --Commented out by Inspection START (21/7/20 9:27 PM):
//    public void setPhotoId(String photoId) {
//        this.photoId = photoId;
//    }
// --Commented out by Inspection STOP (21/7/20 9:27 PM)

// --Commented out by Inspection START (21/7/20 9:27 PM):
//    public void setPhotoUrl(String photoUrl) {
//        this.photoUrl = photoUrl;
//    }
// --Commented out by Inspection STOP (21/7/20 9:27 PM)
}
