package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TelaInicial extends Fragment implements View.OnClickListener {
    View myView;
    Button cadastrar;
    Button listarPerfis;
    Button listarRemedios;

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
        switch (view.getId()){
            case R.id.button:
                break;
            case R.id.button2:
                break;
            case  R.id.button3:
                break;
        }//switch
    }//onClick

}//class
