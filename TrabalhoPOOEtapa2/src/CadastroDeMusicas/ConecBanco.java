/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadastroDeMusicas;

/**
 *
 * @author VINI-SG
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ConecBanco {
    private static ArrayList<Genero> B_musical;
    private static Connection con;
    static int RETURN_GENERATED_KEYS;
    Artista at;
    Album al;
    Musica m;
    String nome;
    int duracao,nota;
    public ConecBanco(Connection con) {
        ConecBanco.B_musical =  new ArrayList<>();
        this.con = con;
    }
    public static Connection getCon() {
        return con;
    }
    public static void setCon(Connection con) {
        ConecBanco.con = con;
    }
    public ConecBanco(Artista at, Album al, Musica m) {
        this.at =  at;
        this.al = al;
        this.m = m;
    }
    public void cadastrar(Genero g,Artista a1,Album a2,Musica m) throws Exception {
        PreparedStatement p;
        p = con.prepareStatement("insert into genero (nome) values ('"+g.getNome()+"');",PreparedStatement.RETURN_GENERATED_KEYS);
        p.execute();
        ResultSet rs = p.getGeneratedKeys();  
        int id = 0;  
        if(rs.next()){  
            id = rs.getInt(1);  
        }      
        p = con.prepareStatement("insert into artista (nome,genero_id) values ('"+a1.getNome()+"','"+id+"');");
        p.execute();    
        p = con.prepareStatement("insert into album (nome,ano,artista_id) values ('"+a2.getNome()+"','"+a2.getAno()+"','"+id+"');");
        p.execute();
        p = con.prepareStatement("insert into musica (nome,nota,duracao,album_id) values ('"+m.getNome()+"','"+m.getNota()+"','"+m.getDuracao()+"','"+id+"');");
        p.execute();
        p.close();
    }

    public void deletar(String m) throws Exception {
        PreparedStatement p;
        p = con.prepareStatement("SELECT id FROM musica WHERE musica.nome= '"+m+"';");
        p.execute();
        ResultSet rs = p.executeQuery();  
        int id = 0;  
        if(rs.next()){  
            id = rs.getInt(1);  
        }
        p = con.prepareStatement("DELETE musica.*, album.*,artista.*,genero.* FROM musica,album,artista,genero "
        + "WHERE musica.id = '"+id+"' AND album.id ='"+id+"' AND artista.id ='"+id+"' AND genero.id ='"+id+"' ");
        p.execute();
        p.close();
    }
    public static ResultSet pesquisar() throws SQLException {
        String m = null,m1,nm = null; int n;
        n = Integer.parseInt(JOptionPane.showInputDialog("Informe se é por:\n1.Musica\n2.Artista\n3.Album\n"));
        switch (n) {
            case 1:
                m="Musica";
                nm="nome";
                break;
            case 2:
                m="Artista";
                nm="nomeAR";
                break;
            case 3:
                m="Album";
                nm="nomeAL";
                break;
        }
        m1 = JOptionPane.showInputDialog(""+m+": ");
        PreparedStatement p;
        Statement st;
        p = con.prepareStatement("SELECT id FROM "+m+" WHERE "+nm+"= '"+m1+"';");
        p.execute();
        ResultSet res; 
        res = p.executeQuery();
        int id = 0;  
        if(res.next()){  
            id = res.getInt(1);  
        }
	try {
            st = con.createStatement();
            return(st.executeQuery("select musica.*, album.*,artista.*,genero.* FROM musica,album,artista,genero "
            + "WHERE musica.id = '"+id+"' AND album.id ='"+id+"' AND artista.id ='"+id+"' AND genero.id ='"+id+"' "));
	} catch (SQLException e) {
            System.out.println("Problema na consulta à tabela genero!");
	}
        p.close();
        return null;
        
    }
    public static ResultSet relatorioGenero() {
	Statement st;
	try {
            st = con.createStatement();
            return(st.executeQuery("SELECT * FROM genero;"));
	} catch (SQLException e) {
            System.out.println("Problema na consulta à tabela genero!");
	}
	return(null);
    }
    public static ResultSet relatorioArtista() {
	Statement st;
	try {
            st = con.createStatement();
            return(st.executeQuery("SELECT * FROM artista;"));
	} catch (SQLException e) {
            System.out.println("Problema na consulta à tabela genero!");
	}
	return(null);
    }
    public static ResultSet relatorioAlbum() {
	Statement st;
	try {
            st = con.createStatement();
            return(st.executeQuery("SELECT * FROM album;"));
	} catch (SQLException e) {
            System.out.println("Problema na consulta à tabela genero!");
	}
	return(null);
    }
    public static ResultSet relatorioMusica() {
	Statement st;
	try {
            st = con.createStatement();
            return(st.executeQuery("SELECT * FROM musica;"));
	} catch (SQLException e) {
            System.out.println("Problema na consulta à tabela genero!");
	}
	return(null);
    }
}