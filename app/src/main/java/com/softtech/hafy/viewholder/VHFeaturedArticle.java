package com.softtech.hafy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.softtech.hafy.R;

public class VHFeaturedArticle extends RecyclerView.ViewHolder {

    //declare item view
    MaterialCardView articleContainer;
    TextView articleTag;
    TextView articleTitle;
    ImageView articleImage;

    public VHFeaturedArticle(@NonNull View itemView) {
        super(itemView);

        //init item view
        articleContainer = itemView.findViewById(R.id.featured_article_container);
        articleTag = itemView.findViewById(R.id.featured_article_tag);
        articleTitle = itemView.findViewById(R.id.featured_article_title);
        articleImage = itemView.findViewById(R.id.featured_article_image);

    }
}
