package com.softlaboratory.hafy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.softlaboratory.hafy.R;

public class VHProfessionalAccount extends RecyclerView.ViewHolder {

    public MaterialCardView container;
    public ImageView profilePic;
    public TextView accountName;
    public TextView job;
    public TextView rating;
    public TextView workExperience;
    public TextView onTime;
    public TextView price;
    public MaterialButton buttonChat;

    public VHProfessionalAccount(@NonNull View itemView) {
        super(itemView);

        container = itemView.findViewById(R.id.item_beranda_proaccount_item_container);
        profilePic = itemView.findViewById(R.id.item_beranda_proaccount_item_pic);
        accountName = itemView.findViewById(R.id.item_beranda_proaccount_item_name);
        job = itemView.findViewById(R.id.item_beranda_proaccount_item_job);
        rating = itemView.findViewById(R.id.item_beranda_proaccount_item_rating);
        workExperience = itemView.findViewById(R.id.item_beranda_proaccount_item_workingexp);
        onTime = itemView.findViewById(R.id.item_beranda_proaccount_item_ontime);
        price = itemView.findViewById(R.id.item_beranda_proaccount_item_price);
        buttonChat = itemView.findViewById(R.id.item_beranda_proaccount_item_btnchat);
    }
}
