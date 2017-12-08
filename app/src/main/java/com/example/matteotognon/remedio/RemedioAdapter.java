package com.example.matteotognon.remedio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daenerys on 11/29/17.
 */

public class RemedioAdapter extends RecyclerView.Adapter<ViewHolderRemedios> {

    private Context context;
    private List<Remedio> listaReceita;
    private Perfil perfil;

    public RemedioAdapter(Context context, List<Remedio> listaReceita, Perfil perfil) {
        this.context = context;
        this.listaReceita = listaReceita;
        this.perfil = perfil;
    }

    @Override
    public ViewHolderRemedios onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartao_remedio, parent, false);

        return new ViewHolderRemedios(v,context, perfil, listaReceita);
    }

    @Override
    public void onBindViewHolder(ViewHolderRemedios holder, int position) {
        holder.getNomeReceita().setText(listaReceita.get(position).getNome());
    }

    @Override
    public int getItemCount() { return listaReceita.size();}
}
