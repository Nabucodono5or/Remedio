package com.example.matteotognon.remedio;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class GerenciamentoPerfil extends Fragment {

    View myView;
    Perfil perfil;
    TextView nome, descricao;
    ImageView fotoUsuario;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_gerenciamento_perfil, container, false);

        nome = myView.findViewById(R.id.textViewNomePerfil);
        descricao = myView.findViewById(R.id.textViewDescricaoPerfil);
        fotoUsuario = myView.findViewById(R.id.imageViewUsuario);

        setTelaUsuario();

        return myView;
    }//onCreateView


    public void setTelaUsuario(){
        nome.setText(perfil.getNome());
        descricao.setText(perfil.getDescricao());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }//onAttach

    @Override
    public void onDetach() {
        super.onDetach();
    }//onDetach

    public void setPerfil(Perfil perfil){
        this.perfil = perfil;
    }

}//fragment
