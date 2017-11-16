package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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
            salvarPerfil();
        }
    }//onClick


    private void salvarPerfil(){
        if(!editTextNome.getText().toString().equals("")){
            //Perfil perfil = new Perfil(editTextNome.getText().toString(), editTextDescricao.getText().toString());

            Perfil perfil = new Perfil();
            perfil.setNome(editTextNome.getText().toString());
            perfil.setDescricao(editTextDescricao.getText().toString());

            //salvar o objeto
            String nomeArq = perfil.getNome();

            try{
                File file = new File(getActivity().getFilesDir(),nomeArq);
                FileOutputStream fo = new FileOutputStream(file);

                ObjectOutputStream oo = new ObjectOutputStream(fo);
                oo.writeObject(perfil);
                oo.close();

                Toast.makeText(getActivity(),"Salvo com sucesso",Toast.LENGTH_SHORT).show();

                limparCampos();
            }catch (Exception e){
                e.getMessage();
            }//tryCatch


        }else {
            Toast.makeText(getActivity(),"preencha o campo nome",Toast.LENGTH_SHORT).show();
        }
    }//salvarPerfil


    private void verificaPerfilRepetido(){
        //É preciso criar uma classe que use o Obter diretorio para buscar Perfis repetidos e impedir
        //suas adições ao aplicativo

        //TODO

    }//verificaPerfilRepetido

    private void limparCampos(){
        editTextNome.setText("");
        editTextDescricao.setText("");
    }//limparCampos

}//class
