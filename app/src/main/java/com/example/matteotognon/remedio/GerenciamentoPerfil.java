package com.example.matteotognon.remedio;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;


public class GerenciamentoPerfil extends Fragment {

    View myView;
    Perfil perfil;
    TextView nome, descricao;
    ImageView fotoUsuario;
    MenuRemedios menuRemedios;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_gerenciamento_perfil, container, false);

        nome = myView.findViewById(R.id.textViewNomePerfil);
        descricao = myView.findViewById(R.id.textViewDescricaoPerfil);
        fotoUsuario = myView.findViewById(R.id.imageViewUsuario);

        setTelaUsuario();

        menuRemedios = new MenuRemedios();
        menuRemedios.setPerfil(perfil);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_remedios, menuRemedios).commit();
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
