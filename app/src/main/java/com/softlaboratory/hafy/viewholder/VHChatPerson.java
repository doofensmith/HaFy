package com.softlaboratory.hafy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softlaboratory.hafy.R;

public class VHChatPerson extends RecyclerView.ViewHolder {

    public TextView message;
    public ImageView targetPic;

    public VHChatPerson(@NonNull View itemView) {
        super(itemView);

        message = itemView.findViewById(R.id.item_chat_message);

    }
}
