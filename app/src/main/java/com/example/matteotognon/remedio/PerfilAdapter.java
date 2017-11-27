package com.example.matteotognon.remedio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * Created by daenerys on 11/15/17.
 */

public class PerfilAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    Context context;
    ArrayList<Perfil> listaPerfis;

    public PerfilAdapter(Context context, ArrayList<Perfil> listaPerfis) {
        this.context = context;
        this.listaPerfis = listaPerfis;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recuperar a view setar o construtor do viewholder com ela
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartao_perfil, parent, false);

        RecyclerViewHolder rvh = new RecyclerViewHolder(v,listaPerfis);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.getUsuario().setText(listaPerfis.get(position).getNome());
    }


    @Override
    public int getItemCount() {
        return listaPerfis.size();
    }
}//class
