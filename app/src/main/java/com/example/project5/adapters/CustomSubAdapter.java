package com.example.project5.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.R;
import com.example.project5.interfaces.OnBottomReachedListener;
import com.example.project5.models.MemberSub;

import java.util.ArrayList;

public class CustomSubAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final Context context;
    private final ArrayList<MemberSub> subMembers;
    private final OnBottomReachedListener onBottomReachedListener;

    public CustomSubAdapter(Context context, ArrayList<MemberSub> subMembers, OnBottomReachedListener onBottomReachedListener) {
        this.context = context;
        this.subMembers = subMembers;
        this.onBottomReachedListener = onBottomReachedListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View footer = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom_layout_view_sub, parent, false);
        return new CustomSubViewHolder(footer);
    }

    boolean isHeader(int position) {
        return position == 0;
    }

    boolean isFooter(int position) {
        return position == subMembers.size() - 1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MemberSub memberSub = subMembers.get(position);

        if (position == subMembers.size() - 1) {
            onBottomReachedListener.onBottomReached(position);
        }
        if (isHeader(position) || isFooter(position)) return;

        // some code
    }

    @Override
    public int getItemCount() {
        return subMembers.size();
    }


    public static class CustomSubViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        public CustomSubViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            // add your UI component here
        }
    }
}
