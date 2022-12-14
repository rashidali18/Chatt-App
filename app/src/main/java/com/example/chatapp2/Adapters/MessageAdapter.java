package com.example.chatapp2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatapp2.MessageActivity;
import com.example.chatapp2.Model.ChatModel;
import com.example.chatapp2.Model.UserModel;
import com.example.chatapp2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter   extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_right = 1;

    private Context mContext;
    private List<ChatModel> mChat;
    private String imageURL;

    FirebaseUser firebaseUser;

    public MessageAdapter(Context mContext, List<ChatModel> mChat,String imageURL){
        this.mContext = mContext;
        this.mChat = mChat;
        this.imageURL = imageURL;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_right){
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right,parent,false);
            return new MessageAdapter.ViewHolder(view);
        }else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left,parent,false);
            return new MessageAdapter.ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        ChatModel chat = mChat.get(position);

        holder.show_msg.setText(chat.getMessage());

        if(imageURL.equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(mContext).load(imageURL).into(holder.profile_image);
        }

        if(position == mChat.size()-1){
            if(chat.isIsseen()){
                holder.txt_seen.setText("seen");
            } else {
                holder.txt_seen.setText("Delivered");
            }
        }else{
            holder.txt_seen.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView show_msg;
        public ImageView profile_image;
        public TextView txt_seen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            show_msg = itemView.findViewById(R.id.show_msg);
            profile_image = itemView.findViewById(R.id.profile_image);
            txt_seen = itemView.findViewById(R.id.txt_seen);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(mChat.get(position).getSender().equals(firebaseUser.getUid())){
            return MSG_TYPE_right;
        }else{
            return MSG_TYPE_LEFT;
        }
    }
}
