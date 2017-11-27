package com.example.matteotognon.remedio;

import android.app.FragmentManager;
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

    public RecyclerViewHolder(View itemView, ArrayList<Perfil> perfis) {
        super(itemView);

        this.perfis = perfis;
        itemView.setOnClickListener(this);
        usuario = (TextView) itemView.findViewById(R.id.textViewUsuario);

    }//RecyclerViewHolder

    public TextView getUsuario() {
        return usuario;
    }

    @Override
    public void onClick(View view) {
        MenuActivity menuActivity = new MenuActivity();
        //usar um método da menuActivity para fazr um transaction para o GerenciamentoPerfil
        //Se isso não dar certo seguir para uma calsse Activity que cria o perfil
        Log.e(TAG, "Elemento "+ getAdapterPosition() + " Clicado.");
    }
}//class
