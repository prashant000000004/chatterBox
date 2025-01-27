package com.example.chatterbox.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatterbox.Models.ChatMessage;
import com.example.chatterbox.Models.User;
import com.example.chatterbox.databinding.ItemContainerRecentConversationBinding;
import com.example.chatterbox.listeners.ConversionListeners;

import java.util.List;

public class RecentConversionAdapter extends RecyclerView.Adapter<RecentConversionAdapter.ConversionViewHolder>{

    private final List<ChatMessage> chatMessages;
    private final ConversionListeners conversionListeners;


    public RecentConversionAdapter(List<ChatMessage> chatMessages, ConversionListeners conversionListeners) {
        this.chatMessages = chatMessages;
        this.conversionListeners = conversionListeners;
    }

    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversionViewHolder(
                ItemContainerRecentConversationBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionViewHolder holder, int position) {
        holder.setData(chatMessages.get(position));

    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }


    class ConversionViewHolder extends RecyclerView.ViewHolder {
        ItemContainerRecentConversationBinding binding;

        ConversionViewHolder(ItemContainerRecentConversationBinding itemContainerRecentConversationBinding) {
            super(itemContainerRecentConversationBinding.getRoot());
            binding = itemContainerRecentConversationBinding;
        }

        void setData(ChatMessage chatMessage) {
            binding.profileImage.setImageBitmap(getConversionImage(chatMessage.conversionImage));
            binding.txtName.setText(chatMessage.conversionName);
            binding.txtRecentMessage.setText(chatMessage.message);
            binding.getRoot().setOnClickListener(v->{
                User user=new User();
                user.id=chatMessage.conversionId;
                user.name=chatMessage.conversionName;
                user.image=chatMessage.conversionImage;
                conversionListeners.onConversionClicked(user);
            });

        }
    }


        private Bitmap getConversionImage(String encodedImage) {
            byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
    }

