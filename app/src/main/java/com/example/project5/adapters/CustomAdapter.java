package com.example.project5.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.R;
import com.example.project5.interfaces.OnBottomReachedListener;
import com.example.project5.models.Member;
import com.example.project5.models.MemberSub;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM_VIEW = 0;
    private static final int TYPE_ITEM_LIST = 1;

    public Context context;
    public ArrayList<Member> members;
    private final OnBottomReachedListener onBottomReachedListener;

    public CustomAdapter(Context context, ArrayList<Member> members, OnBottomReachedListener onBottomReachedListener) {
        this.context = context;
        this.members = members;
        this.onBottomReachedListener = onBottomReachedListener;
    }

    @Override
    public int getItemViewType(int position) {
        Member member = members.get(position);
        if (member.getSubList() != null) {
            return TYPE_ITEM_LIST;
        }
        return TYPE_ITEM_VIEW;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_LIST) {
            View header = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom_layout_list, parent, false);
            return new CustomListHolder(header);
        }
        View footer = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom_layout_view, parent, false);
        return new CustomViewHolder(footer);

    }

    boolean isHeader(int position) {
        return position == 0;
    }

    boolean isFooter(int position) {
        return position == members.size() - 1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Member member = members.get(position);

        if (position == members.size() - 1) {
            onBottomReachedListener.onBottomReached(position);
        }
        if (isHeader(position) || isFooter(position)) return;

        if (holder instanceof CustomViewHolder) {
            // some code here
        }

        if (holder instanceof CustomListHolder) {
            RecyclerView recyclerView = ((CustomListHolder) holder).getRecyclerView();

            ArrayList<MemberSub> subMembers = member.getSubList();
            refreshAdapter(recyclerView, subMembers);
        }
    }

    void refreshAdapter(RecyclerView recyclerView, ArrayList<MemberSub> subMembers) {
        CustomSubAdapter customSubAdapter = new CustomSubAdapter(context, subMembers, new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                Toast.makeText(context, "Sub RV Bottom!", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(customSubAdapter);
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            // add you UI component here
        }
    }

    public static class CustomListHolder extends RecyclerView.ViewHolder {
        public final View view;
        private final RecyclerView recyclerView;

        public CustomListHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            // add your UI component here

            recyclerView = view.findViewById(R.id.sub_item_recycler_view);
        }

        public RecyclerView getRecyclerView() {
            return recyclerView;
        }
    }

    @Override
    public int getItemCount() {
        return members.size();
    }
}
