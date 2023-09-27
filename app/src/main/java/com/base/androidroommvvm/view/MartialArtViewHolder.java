package com.base.androidroommvvm.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.base.androidroommvvm.R;

public class MartialArtViewHolder extends RecyclerView.ViewHolder {

    private final TextView txtMartialArt;



    private MartialArtViewHolder(View itemView) {

        super(itemView);



        txtMartialArt = itemView.findViewById(R.id.txtMartialArt);

    }



    public void bind(String text) {

        txtMartialArt.setText(text);

    }



    public static MartialArtViewHolder create(ViewGroup parent) {



        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);

        return new MartialArtViewHolder(view);



    }



}
