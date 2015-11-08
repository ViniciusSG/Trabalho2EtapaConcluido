/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadastroDeMusicas;

/**
 *
 * @author VINI
 */
import java.util.ArrayList;
public class Genero {
    private ArrayList<Artista> artista;
    private String nome;

    public Genero(String nome) {
        this.artista = new ArrayList<>();
        this.nome = nome;
    }

    public ArrayList<Artista> getArtista() {
        return artista;
    }

    public void setArtista(ArrayList<Artista> artista) {
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
