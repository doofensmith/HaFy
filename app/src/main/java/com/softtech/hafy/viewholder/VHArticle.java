package com.softtech.hafy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softtech.hafy.R;

public class VHArticle extends RecyclerView.ViewHolder {

    //declare view
    LinearLayout container;
    ImageView articleImage;
    TextView articleTitle;

    public VHArticle(@NonNull View itemView) {
        super(itemView);

        //init view
        container = itemView.findViewById(R.id.item_article_container);
        articleImage = itemView.findViewById(R.id.item_article_image);
        articleTitle = itemView.findViewById(R.id.item_article_title);
    }
}
