package com.example.matteotognon.remedio;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daenerys on 11/29/17.
 */

public class ViewHolderRemedios extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context context;
    private TextView nomeReceita;
    private GerenciandoPath gerenciandoPath;
    private Perfil perfil;
    private List<Remedio> listaReceitas;
    private static final String TAG = "RecyclerView ";
    private String nomeRec;
    private Remedio r;


    public ViewHolderRemedios(View itemView, Context context, Perfil perfil, List<Remedio> listaReceitas) {
        super(itemView);

        this.listaReceitas = new ArrayList<>();
        this.listaReceitas = listaReceitas;
        this.perfil = perfil;
        this.context = context;
        nomeReceita = itemView.findViewById(R.id.textViewNomeRemedio);
        itemView.setOnClickListener(this);

    }//ViewHolderRemedios

    public TextView getNomeReceita() {
        return nomeReceita;
    }

    @Override
    public void onClick(View view) {
        r = listaReceitas.get(getAdapterPosition());
        gerenciandoPath = (GerenciandoPath) context;
        gerenciandoPath.setEditDeleteRemedio(perfil, r);

        Log.e(TAG, "Elemento "+ getAdapterPosition() +" Clicado.");
    }
}
