package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by MatteoTognon on 07/11/2017.
 */

public class MenuEditarRemedio extends Fragment implements View.OnClickListener {
    Perfil perfil;
    View myView;
    EditText nomeRemedio, quant, intervalo;
    int qx, ix;
    Remedio remedio;
    ArrayList<EditText> listaEntrada;
    Button btnSalvarRemedio;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_editar_remedio, container, false);

        nomeRemedio = myView.findViewById(R.id.editTextNomeRemedio);
        quant = myView.findViewById(R.id.editTextQuantidade);
        intervalo = myView.findViewById(R.id.editTextIntervalo);
        btnSalvarRemedio = myView.findViewById(R.id.buttonSalvar);

        listaEntrada = new ArrayList<>();

        listaEntrada.add(nomeRemedio);
        listaEntrada.add(quant);
        listaEntrada.add(intervalo);

        btnSalvarRemedio.setOnClickListener(this);

        return myView;
    }


    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }


    public void onClickSalvarRemedio(){

        if(!isEmpty()){
            converterIntervaloQuantidade(quant.getText().toString(), intervalo.getText().toString());
            remedio = new Remedio(nomeRemedio.getText().toString(),qx,ix);

            limparCampos();
        }else {
            Toast.makeText(getActivity(), "preencha todos os campos",Toast.LENGTH_SHORT).show();
        }
    }


    public void converterIntervaloQuantidade(String q, String i){
        qx = Integer.parseInt(q);
        ix = Integer.parseInt(i);
    }


    private boolean isEmpty(){
        for (EditText e: listaEntrada) {
            if(e.getText().toString().equals("")){
                return true;
            }//if e
        }//foreach
        return false;
    }//isEmpty


    private void limparCampos(){
        for (EditText e: listaEntrada) {
            e.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        onClickSalvarRemedio();
    }
}
