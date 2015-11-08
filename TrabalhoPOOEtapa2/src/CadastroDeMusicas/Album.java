package CadastroDeMusicas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author VINI
 */
import CadastroDeMusicas.Musica;
import java.util.ArrayList;
public class Album {
    private ArrayList<Musica> musica;
    private String nome;
    private int ano;

    public Album(String nome, int ano) {
        this.musica = new ArrayList<>();
        this.nome = nome;
        this.ano = ano;
    }

    public ArrayList<Musica> getMusica() {
        return musica;
    }

    public void setMusica(ArrayList<Musica> musica) {
        this.musica = musica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
}
