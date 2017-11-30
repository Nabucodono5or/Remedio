package com.example.matteotognon.remedio;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by daenerys on 11/29/17.
 */

public class ViewHolderRemedios extends RecyclerView.ViewHolder {
    private Context context;
    private TextView nomeReceita;


    public ViewHolderRemedios(View itemView, Context context) {
        super(itemView);

        this.context = context;
        nomeReceita = itemView.findViewById(R.id.textViewNomeRemedio);

    }//ViewHolderRemedios

    public TextView getNomeReceita() {
        return nomeReceita;
    }
}
