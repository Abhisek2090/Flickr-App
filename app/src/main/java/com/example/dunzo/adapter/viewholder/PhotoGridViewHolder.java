package com.example.dunzo.adapter.viewholder;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dunzo.R;
import com.example.dunzo.adapter.PhotoGridAdapter;
import com.example.dunzo.model.PhotoListModel;
import com.google.android.flexbox.FlexboxLayoutManager;


public class PhotoGridViewHolder extends RecyclerView.ViewHolder {


    private TextView titleTextView;
    private ImageView imageView;
    private Context context;

    public PhotoGridViewHolder(@NonNull View itemView) {
        super(itemView);
        this.context = itemView.getContext();
        initViews(itemView);

    }

    private void initViews(View itemView) {
        titleTextView= (TextView)itemView.findViewById(R.id.textView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);

    }

    public void bindData(final PhotoListModel photo) {
        String image_url = photo.getUrl();
        String title = photo.getTitle();


        titleTextView.setText(title);

        Glide.with(context)
                .load(image_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);

    }
}
