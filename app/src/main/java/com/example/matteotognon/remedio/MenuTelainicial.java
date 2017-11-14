package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by MatteoTognon on 07/11/2017.
 */

public class MenuTelainicial extends Fragment implements View.OnClickListener {

    View myView;
    Button cadastrar;
    Button listarPerfis;
    Button listarRemedios;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_tela_inicial, container, false);

        cadastrar =  myView.findViewById(R.id.button);
        listarPerfis =  myView.findViewById(R.id.button2);
        listarRemedios =  myView.findViewById(R.id.button3);

        cadastrar.setOnClickListener(this);
        listarPerfis.setOnClickListener(this);
        listarRemedios.setOnClickListener(this);

        return myView;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (view.getId()){
            case R.id.button:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuEditarPerfil()).commit();
                break;
            case R.id.button2:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuPerfis()).commit();
                break;
            case  R.id.button3:
                //por enqunto deixarei Menu remédios a única responsável por essa terefa - listar Remédios
                fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuRemedios()).commit();
                break;
        }//switch
    }//onClick
}
