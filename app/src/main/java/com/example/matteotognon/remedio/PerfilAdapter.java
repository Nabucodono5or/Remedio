package com.example.matteotognon.remedio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * Created by daenerys on 11/15/17.
 */

public class PerfilAdapter extends RecyclerView.Adapter{

    Context context;
    ArrayList<Perfil> listaPerfis;

    public PerfilAdapter(Context context, ArrayList<Perfil> listaPerfis) {
        this.context = context;
        this.listaPerfis = listaPerfis;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recuperar a view setar o construtor do viewholder com ela
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //o retorno do método onCreateViewHolder retorna o view holder setado lá como holder
        //então eu acesso sues metodos e seto de acordo com o arrayrecebido os dados.
    }

    @Override
    public int getItemCount() {
        return listaPerfis.size();
    }
}//class
