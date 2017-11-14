import java.io.Serializable;

/**
 * Created by daenerys on 11/14/17.
 */

public class Remedio implements Serializable {
    private String nome;
    private int intervalo;
    private int quantidade;


    public Remedio(String nome, int intervalo, int quntidade) {
        this.nome = nome;
        this.intervalo = intervalo;
        this.quantidade = quntidade;
    }//construtor

    public String getNome() { return nome; }

    public void setNome(String nome) {this.nome = nome;}

    public int getIntervalo() {return intervalo;}

    public void setIntervalo(int intervalo) {this.intervalo = intervalo;}

    public int getQuntidade() {return quantidade;}

    public void setQuntidade(int quntidade) {this.quantidade = quntidade;}

}//class
