package com.example.chatterbox.Adapter;


import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatterbox.Models.ChatMessage;
import com.example.chatterbox.databinding.ItemContainerRecievedBinding;
import com.example.chatterbox.databinding.ItemContainerSetMessageBinding;

import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<ChatMessage> chatMessages;
    private  Bitmap receiverProfileImage;
    private final String senderId;

    private static final int VIEW_TYPE_SENT=1;
    private static  final int VIEW_TYPE_RECEIVED=2;

    public void setReceiverProfileImage(Bitmap bitmap){
        receiverProfileImage=bitmap;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==VIEW_TYPE_SENT){
            return new SetMessageViewHolder(
                    ItemContainerSetMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }else{
            return new ReceivedViewHolder(ItemContainerRecievedBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false
            )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==VIEW_TYPE_SENT){
            ((SetMessageViewHolder)holder).setData(chatMessages.get(position));
        }else {
            ((ReceivedViewHolder)holder).setData(chatMessages.get(position),receiverProfileImage);
        }

    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (chatMessages.get(position).senderId.equals(senderId)){
            return VIEW_TYPE_SENT;
        }else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    public ChatAdapter(List<ChatMessage> chatMessages, Bitmap receiverProfileImage, String senderId) {
        this.chatMessages = chatMessages;
        this.receiverProfileImage = receiverProfileImage;
        this.senderId = senderId;
    }

    static class SetMessageViewHolder extends RecyclerView.ViewHolder{
        private final ItemContainerSetMessageBinding binding;
        SetMessageViewHolder(ItemContainerSetMessageBinding itemContainerSetMessageBinding){
            super(itemContainerSetMessageBinding.getRoot());
            binding=itemContainerSetMessageBinding;
        }

        void setData(ChatMessage chatMessage){
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
        }

    }

    static class ReceivedViewHolder extends RecyclerView.ViewHolder{
        private final ItemContainerRecievedBinding binding;
        ReceivedViewHolder(ItemContainerRecievedBinding itemContainerRecievedBinding){
            super(itemContainerRecievedBinding.getRoot());
            binding=itemContainerRecievedBinding;
        }
        void setData(ChatMessage chatMessage,Bitmap receiverProfileImage){
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);

            if(receiverProfileImage!=null) {
                binding.profileImage.setImageBitmap(receiverProfileImage);
            }
        }
    }

}
