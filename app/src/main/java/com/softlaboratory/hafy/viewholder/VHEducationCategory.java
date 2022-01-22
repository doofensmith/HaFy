package com.softlaboratory.hafy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.softlaboratory.hafy.R;

public class VHEducationCategory extends RecyclerView.ViewHolder {

    public MaterialCardView container;
    public ImageView image;
    public TextView category;

    public VHEducationCategory(@NonNull View itemView) {
        super(itemView);

        container = itemView.findViewById(R.id.item_beranda_edukasi_item_container);
        image = itemView.findViewById(R.id.item_beranda_edukasi_item_image);
        category = itemView.findViewById(R.id.item_beranda_edukasi_item_category);

    }
}
