package com.example.matteotognon.remedio;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GerenciamentoPerfil extends Fragment {

    View myView;


    public GerenciamentoPerfil() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_gerenciamento_perfil, container, false);

        //TODO construir o layout e carregar a classe enviado por uma intent

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

}//fragment
