package com.example.matteotognon.remedio;

import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by daenerys on 11/14/17.
 */

public class Perfil {
    private String nome;
    private String descricao;
    ArrayList<Remedio> remedios;

    public String getNome() {return nome;}

    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao;}

    public void setDescricao(String descricao) { this.descricao = descricao;}


    public Perfil(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        remedios = new ArrayList<Remedio>();
    }

    public void addRemedio(Remedio remedio){
        if((remedios != null) && (!remedios.isEmpty())){
            for (Remedio r: remedios) {
                if(!remedio.getNome().equals(r.getNome())){
                    remedios.add(remedio);
                }else {
                    mensagem();
                }
            }//for
        }else {
            Log.e(TAG, "Não foi salvo o remedio");
        }
    }//addRemedio


    public void removeRemedio(Remedio remedio){
        if(!remedios.isEmpty()){
            for (Remedio r: remedios) {
                remedios.remove(remedio);
            }//for
        }//if
    }//removeRemedio


    public String mensagem(){
     return null;
    }//mensagem

}//class
