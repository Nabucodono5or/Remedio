package com.example.matteotognon.remedio;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by daenerys on 11/16/17.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = "RecyclerView ";
    private TextView usuario;
    private ArrayList<Perfil> perfis;
    private Context context;
    private GerenciandoPath gerenciandoPath;

    public RecyclerViewHolder(View itemView, ArrayList<Perfil> perfis ) {
        super(itemView);

        //setar context
        this.perfis = perfis;
        itemView.setOnClickListener(this);
        usuario = (TextView) itemView.findViewById(R.id.textViewUsuario);

    }//RecyclerViewHolder

    public TextView getUsuario() {
        return usuario;
    }

    @Override
    public void onClick(View view) {
        // vou precisar de entrada do contexto no construtor RecyclerViewHolder
        // então a classe será gerada desse jeito gerenciandoPath = (GerenciandoPath) context;
        // gerenciandoPath.setFragment(perfis.get(getAdapterPosition);

        Log.e(TAG, "Elemento "+ getAdapterPosition() + " Clicado.");
    }
}//class
