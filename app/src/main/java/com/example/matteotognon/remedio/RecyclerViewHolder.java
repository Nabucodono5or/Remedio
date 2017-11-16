package com.example.matteotognon.remedio;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by daenerys on 11/16/17.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView usuario;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        usuario = (TextView) itemView.findViewById(R.id.textViewUsuario);

    }//RecyclerViewHolder

    public TextView getUsuario() {
        return usuario;
    }
}//class
