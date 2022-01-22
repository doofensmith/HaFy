package com.softlaboratory.hafy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softlaboratory.hafy.R;

public class VHHomeArticle extends RecyclerView.ViewHolder {

    public LinearLayout container;
    public ImageView image;
    public TextView title;

    public VHHomeArticle(@NonNull View itemView) {
        super(itemView);

        container = itemView.findViewById(R.id.item_beranda_article_container);
        image = itemView.findViewById(R.id.item_beranda_article_image);
        title = itemView.findViewById(R.id.item_beranda_article_title);
    }
}
