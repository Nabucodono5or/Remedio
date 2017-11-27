package com.example.matteotognon.remedio;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by daenerys on 11/16/17.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = "RecyclerView ";
    private TextView usuario;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
        usuario = (TextView) itemView.findViewById(R.id.textViewUsuario);

    }//RecyclerViewHolder

    public TextView getUsuario() {
        return usuario;
    }

    @Override
    public void onClick(View view) {
        Log.e(TAG, "Elemento "+ getAdapterPosition() + " Clicado.");
    }
}//class
