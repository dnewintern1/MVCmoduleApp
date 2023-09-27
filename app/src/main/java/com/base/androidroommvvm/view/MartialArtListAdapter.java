package com.base.androidroommvvm.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.base.androidroommvvm.room_database.MartialArt;

public class MartialArtListAdapter extends ListAdapter<MartialArt, MartialArtViewHolder> {

    private  ListItemLongClickListner mListItemLongClickListner;

    public MartialArtListAdapter(ListItemLongClickListner listItemLongClickListner,@NonNull DiffUtil.ItemCallback<MartialArt> diffCallback) {

        super(diffCallback);

        mListItemLongClickListner = listItemLongClickListner;

    }



    @NonNull

    @Override

    public MartialArtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return MartialArtViewHolder.create(parent);

    }



    @Override

    public void onBindViewHolder(@NonNull MartialArtViewHolder holder, int position) {



        MartialArt currentMartialArtObject = getItem(position);

        holder.bind(currentMartialArtObject.getFavmaterialArt());

        holder.itemView.setOnLongClickListener(view -> {

            mListItemLongClickListner.listItemLongClicked(currentMartialArtObject);

            return true;

        });

    }





    public static class MartialArtDiff extends DiffUtil.ItemCallback<MartialArt> {



        @Override

        public boolean areItemsTheSame(@NonNull MartialArt oldItem, @NonNull MartialArt newItem) {

            return oldItem == newItem;

        }



        @Override

        public boolean areContentsTheSame(@NonNull MartialArt oldItem, @NonNull MartialArt newItem) {

            return oldItem.getFavmaterialArt().equals(newItem.getFavmaterialArt());

        }

    }



}



