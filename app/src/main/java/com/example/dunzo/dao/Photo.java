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

    @ColumnInfo(name = "PhotoTitle")
    private String photoTitle;

    public Photo(String photoId, String photoUrl, String photoTitle) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
        this.photoTitle = photoTitle;
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

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }
}
