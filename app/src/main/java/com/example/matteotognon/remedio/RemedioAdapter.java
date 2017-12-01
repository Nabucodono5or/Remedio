package com.example.matteotognon.remedio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by daenerys on 11/29/17.
 */

public class RemedioAdapter extends RecyclerView.Adapter<ViewHolderRemedios> {

    private Context context;
    private ArrayList<Remedio> listaReceita;

    public RemedioAdapter(Context context, ArrayList<Remedio> listaReceita) {
        this.context = context;
        this.listaReceita = listaReceita;
    }

    @Override
    public ViewHolderRemedios onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartao_remedio, parent, false);

        return new ViewHolderRemedios(v,context);
    }

    @Override
    public void onBindViewHolder(ViewHolderRemedios holder, int position) {
        holder.getNomeReceita().setText(listaReceita.get(position).getNome());
    }

    @Override
    public int getItemCount() { return listaReceita.size();}
}