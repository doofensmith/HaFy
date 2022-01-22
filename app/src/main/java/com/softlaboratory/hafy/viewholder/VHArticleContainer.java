package com.softlaboratory.hafy.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softlaboratory.hafy.R;

public class VHArticleContainer extends RecyclerView.ViewHolder {

    public TextView articleTag;
    public TextView btnSeeAll;
    public RecyclerView itemArticle;

    public VHArticleContainer(@NonNull View itemView) {
        super(itemView);

        articleTag = itemView.findViewById(R.id.item_article_container_article_tag);
        btnSeeAll = itemView.findViewById(R.id.item_article_container_btn_see_all);
        itemArticle = itemView.findViewById(R.id.item_article_container_recyclerview);
    }
}
