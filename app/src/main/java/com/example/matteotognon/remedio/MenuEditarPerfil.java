package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by MatteoTognon on 07/11/2017.
 */

public class MenuEditarPerfil extends Fragment implements View.OnClickListener{
    View myView;
    Button btnSalvar;
    EditText editTextNome;
    EditText editTextDescricao;

    ArrayList<EditText> listaEntrada;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_editar_perfil, container, false);

        btnSalvar = myView.findViewById(R.id.btnSalvar);
        editTextDescricao = myView.findViewById(R.id.editTextDescricao);
        editTextNome = myView.findViewById(R.id.editTextNome);

        listaEntrada = new ArrayList<>();
        listaEntrada.add(editTextDescricao);
        listaEntrada.add(editTextNome);

        btnSalvar.setOnClickListener(this);
        return myView;
    }//onCreateView

    private boolean isEmpty(){
        for (EditText e: listaEntrada) {
            if(e.getText().toString().equals("")){
                return true;
            }//if e
        }//foreach
        return false;
    }//isEmpty


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnSalvar){
            Toast.makeText(getActivity(),"botão pressionado",Toast.LENGTH_SHORT).show();
        }
    }//onClick

    private void salvarPerfil(){
        if(editTextNome.getText().toString().equals("")){
            //criar o objeto
            //salvar o objeto
        }
    }//salvarPerfil

}//class
