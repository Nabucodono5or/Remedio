import android.widget.Toast;

import com.example.matteotognon.remedio.Remedios;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by daenerys on 11/14/17.
 */

public class Perfil implements Serializable {
    private String nome;
    private String descricao;
    ArrayList<Remedio> remedios;

    Perfil(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
        remedios = new ArrayList<>();
    }//cosntrutor


    Perfil(String nome){
        this.nome = nome;
        remedios = new ArrayList<>();
    }//construtor2

    public String getNome() {return nome;}

    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao;}

    public void setDescricao(String descricao) { this.descricao = descricao;}


    public void addRemedio(Remedio remedio){
        if(!remedios.isEmpty()){
            for (Remedio r: remedios) {
                if(!remedio.getNome().equals(r.getNome())){
                    remedios.add(remedio);
                }else {
                    mensagem();
                }
            }//for
        }//if
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
