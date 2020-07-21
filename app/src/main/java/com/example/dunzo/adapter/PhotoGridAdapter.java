package com.example.dunzo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.example.dunzo.R;
import com.example.dunzo.adapter.viewholder.PhotoGridViewHolder;
import com.example.dunzo.model.PhotoListModel;

import org.w3c.dom.Text;

import java.util.List;

public class PhotoGridAdapter extends RecyclerView.Adapter<PhotoGridViewHolder> {

    private List<PhotoListModel> mPhotos;

    public PhotoGridAdapter(List<PhotoListModel> photos) {
        mPhotos = photos;
    }

    public void setDataSource(List<PhotoListModel> photos) {
        if (mPhotos != null) {
            mPhotos.clear();
        }
        mPhotos = photos;
    }

    public void addPhotos(List<PhotoListModel> photos) {
        if (mPhotos != null) {
            mPhotos.addAll(photos);
        }
    }

    @NonNull
    @Override
    public PhotoGridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PhotoGridViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_photo_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoGridViewHolder photoGridViewHolder, int i) {
        ((PhotoGridViewHolder)photoGridViewHolder).bindData(getItemByPosition(i));
    }


    @Override
    public int getItemCount() {
        return mPhotos != null ? mPhotos.size() : 0;
    }

    public PhotoListModel getItemByPosition(int position) {
        return mPhotos.get(position);
    }
}
