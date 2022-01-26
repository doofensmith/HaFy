package com.softlaboratory.hafy.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softlaboratory.hafy.R;

public class VHHomeContainer extends RecyclerView.ViewHolder {

    //DECLARE VIEW
    public TextView title;
    public TextView seeAll;
    public RecyclerView recyclerView;

    public VHHomeContainer(@NonNull View itemView) {
        super(itemView);

        //INIT VIEW
        title = itemView.findViewById(R.id.item_home_container_title);
        seeAll = itemView.findViewById(R.id.item_home_container_seeall);
        recyclerView = itemView.findViewById(R.id.item_home_container_recyclerview);

    }
}
