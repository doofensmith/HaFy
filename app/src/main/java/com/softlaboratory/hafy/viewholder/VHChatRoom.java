package com.softlaboratory.hafy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softlaboratory.hafy.R;

public class VHChatRoom extends RecyclerView.ViewHolder {

    public LinearLayout container;
    public ImageView profilePic;
    public TextView name;
    public TextView lastChat;

    public VHChatRoom(@NonNull View itemView) {
        super(itemView);

        container = itemView.findViewById(R.id.item_chat_container);
        profilePic = itemView.findViewById(R.id.item_chat_image);
        name = itemView.findViewById(R.id.item_chat_name);
        lastChat = itemView.findViewById(R.id.item_chat_lastchat);

    }
}
