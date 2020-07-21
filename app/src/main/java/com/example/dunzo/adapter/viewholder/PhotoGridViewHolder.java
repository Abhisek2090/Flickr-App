package com.example.dunzo.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.flexbox.FlexboxLayoutManager;


public class PhotoGridViewHolder extends RecyclerView.ViewHolder {

    public PhotoGridViewHolder(@NonNull View itemView) {
        super(itemView);
        FlexboxLayoutManager.LayoutParams layoutParams = (FlexboxLayoutManager.LayoutParams) itemView.getLayoutParams();
        layoutParams.setFlexGrow(1f);
    }
}
