package com.example.matteotognon.remedio;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by daenerys on 11/14/17.
 */

public class Perfil {
    private String nome;
    private String descricao;
    //List<Remedio> remedios;
    private HashMap<String, Remedio> remedios = new HashMap<>();

    public String getNome() {return nome;}

    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao;}

    public void setDescricao(String descricao) { this.descricao = descricao;}

    public Perfil() {

    }

    public Perfil(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        //remedios = new ArrayList<Remedio>();
    }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nome", nome);
        result.put("descriçao", descricao);
        result.put("remedios", remedios);

        return result;
    }

    public void addRemedio(Remedio remedio){
        if(remedio != null){
            remedios.put(remedio.getNome(),remedio);
        }else {
            Log.e(TAG,"Não foi salvo o remedio");
        }
    }

    public HashMap<String, Remedio> getRemedios() {
        return remedios;
    }

    public void setRemedios(HashMap<String, Remedio> remedios) {
        this.remedios = remedios;
    }


    public String mensagem(){
     return null;
    }//mensagem

}//class
