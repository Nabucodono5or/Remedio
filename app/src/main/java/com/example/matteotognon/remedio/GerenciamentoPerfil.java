package com.example.matteotognon.remedio;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;


public class GerenciamentoPerfil extends Fragment {

    View myView;
    Perfil perfil;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_gerenciamento_perfil, container, false);

        return myView;
    }//onCreateView


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
