package com.softlaboratory.hafy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.softlaboratory.hafy.R;

public class VHFeaturedArticle extends RecyclerView.ViewHolder {

    //declare item view
    public MaterialCardView articleContainer;
    public TextView articleTag;
    public TextView articleTitle;
    public ImageView articleImage;

    public VHFeaturedArticle(@NonNull View itemView) {
        super(itemView);

        //init item view
        articleContainer = itemView.findViewById(R.id.featured_article_container);
        articleTag = itemView.findViewById(R.id.featured_article_tag);
        articleTitle = itemView.findViewById(R.id.featured_article_title);
        articleImage = itemView.findViewById(R.id.featured_article_image);

    }
}
