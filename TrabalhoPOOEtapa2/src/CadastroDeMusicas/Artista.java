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
public class Artista {
    private ArrayList<Album> album;
    private String nome;

    public Artista(String nome) {
        this.album = new ArrayList<>();
        this.nome = nome;
    }

    public ArrayList<Album> getAlbum() {
        return album;
    }

    public void setAlbum(ArrayList<Album> album) {
        this.album = album;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
