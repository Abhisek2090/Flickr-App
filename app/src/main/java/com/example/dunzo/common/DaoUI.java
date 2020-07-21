package com.example.dunzo.common;

import android.support.annotation.Nullable;

import com.example.dunzo.dao.Photo;
import com.example.dunzo.model.PhotoListModel;

public class DaoUI {

    @Nullable
    public static PhotoListModel toUi(Photo photo) {
        PhotoListModel photoListModel = null;
        if (photo != null) {
            photoListModel = new PhotoListModel();
            photoListModel.setUrl(photo.getPhotoUrl());
            photoListModel.setTitle(photo.getPhotoTitle());
        }

        return photoListModel;
    }
}
