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
	


import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class Principal {
    private static Connection con;
    public Principal() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/bibliotecamusical","root","");
        System.out.println("Conectado com Sucesso.");
        ConecBanco c = new ConecBanco (con);
    }
    
    public static void main(String []args) throws Exception{
        ConecBanco c = new ConecBanco (con);
        Genero g;
        Artista a1;
        Album a2;
        Musica m;
        String nome,tipo;
        int nota,duracao,ano;
        int opc;
        try{
           new Principal();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Não conectado.");
        }
        
        do{
           opc = Integer.parseInt(JOptionPane.showInputDialog("Informe a OPÇÂO:\n1.Inserir\n2.Deletar\n3.Pesquisar\n4.Relatorio em Pdf\n5.Sair"));
           switch(opc){
               case 1:
                    nome = JOptionPane.showInputDialog("Genero: ");
                    g = new Genero(nome);
                    nome = JOptionPane.showInputDialog("Artista: ");
                    a1 = new Artista(nome);
                    nome = JOptionPane.showInputDialog("Album: ");
                    ano = Integer.parseInt(JOptionPane.showInputDialog("Ano: "));
                    a2 = new Album(nome,ano);
                    nome = JOptionPane.showInputDialog("Musica: ");
                    nota = Integer.parseInt(JOptionPane.showInputDialog("Nota (1/10): "));
                    duracao = Integer.parseInt(JOptionPane.showInputDialog("Duração: "));
                    m = new Musica(nome,nota,duracao);
                    c.cadastrar(g,a1,a2,m);
                   break;
               case 2:
                    System.out.println("DELETAR MUSICA: ");
                    nome = JOptionPane.showInputDialog("Musica: ");
                    c.deletar(nome);
                    break;
               case 3:
                    System.out.println("PESQUISAR MUSICA: ");
                    ResultSet rs = ConecBanco.pesquisar();
                    if(rs!=null) {
			try {
                            while(rs.next()) {
				System.out.println("MUSICA:  "+rs.getString("nome"));
                                System.out.println("NOTA:  "+rs.getString("nota"));
                                System.out.println("DURAÇÃO:  "+rs.getString("duracao"));
                                System.out.println("ALBUM:  "+rs.getString("nomeAL"));
                                System.out.println("ANO:  "+rs.getString("ano"));
                                System.out.println("ARTISTA:  "+rs.getString("nomeAR"));
                                System.out.println("GENERO:  "+rs.getString("nomeGE"));
                                
                            }
			} catch (SQLException e) {
				System.out.println("Problema para exibir registros!");
			}
                    } else {
			System.out.println("A pesquisa não retornou nenhum registro!");
                    }
                   break;
               case 4:
                    Document doc = null;
                    OutputStream os = null;
                    String add;
                    System.out.println("LISTA DE MUSICAS: ");
                    try {
                    doc = new Document(PageSize.A4, -1, -1, 25, 1) {};
                    os = new FileOutputStream("Relatorio.pdf");
                    PdfWriter.getInstance((com.itextpdf.text.Document) doc, os);
                    doc.open();
                    Image img = Image.getInstance("images.jpg");
                    img.setAlignment(Element.ALIGN_CENTER);
                    doc.add(img);
                    PdfPTable TB1,TB2,TB3,TB4;
                    TB1 = new PdfPTable(2);
                    TB2 = new PdfPTable(3);
                    TB3 = new PdfPTable(4);
                    TB4 = new PdfPTable(5);
                    PdfPCell T1,T2,T3,T4,T5;
                    T1 = new PdfPCell(new Paragraph("\nCADASTRO DE MUSICAS\n"));
                    T1.setColspan(2);
                    TB1.addCell(T1);
                    T2 = new PdfPCell(new Paragraph("\nGENEROS\n"));
                    T2.setColspan(2);
                    TB1.addCell(T2);
                    ResultSet res,res1,res2,res3;
                    res = ConecBanco.relatorioGenero();
                    if(res!=null) {
			try {
                            while(res.next()) {
				TB1.addCell("ID:  "+res.getString("id"));
                                TB1.addCell("NOME:  "+res.getString("nomeGE"));
                                
                            }
                            doc.add(TB1);
			} catch (SQLException e) {
				System.out.println("Problema para exibir registros!");
			}
                    } else {
			System.out.println("A pesquisa não retornou nenhum registro!");
                    }
                    T3 = new PdfPCell(new Paragraph("\nARTISTAS\n"));
                    T3.setColspan(3);
                    TB2.addCell(T3);
                    res1 = ConecBanco.relatorioArtista();
                    if(res1!=null) {
			try {
                            while(res1.next()) {
				TB2.addCell("ID:  "+res1.getString("id"));
                                TB2.addCell("NOME:  "+res1.getString("nomeAR"));
                                TB2.addCell("GENERO ID:  "+res1.getString("genero_id"));
                            }
                            doc.add(TB2);
			} catch (SQLException e) {
				System.out.println("Problema para exibir registros!");
			}
                    } else {
			System.out.println("A pesquisa não retornou nenhum registro!");
                    }
                    T4 = new PdfPCell(new Paragraph("\nALBUM\n"));
                    T4.setColspan(4);
                    TB3.addCell(T4);
                    res2 = ConecBanco.relatorioAlbum();
                    if(res2!=null) {
			try {
                            while(res2.next()) {
				TB3.addCell("ID:  "+res2.getString("id"));
                                TB3.addCell("NOME:  "+res2.getString("nomeAL"));
                                TB3.addCell("ANO:  "+res2.getString("ano"));
                                TB3.addCell("ARTISTA ID:  "+res2.getString("artista_id"));
                            }
                            doc.add(TB3);
			} catch (SQLException e) {
				System.out.println("Problema para exibir registros!");
			}
                    } else {
			System.out.println("A pesquisa não retornou nenhum registro!");
                    }
                    T5 = new PdfPCell(new Paragraph("\nMUSICA\n"));
                    T5.setColspan(5);
                    TB4.addCell(T5);
                    res3 = ConecBanco.relatorioMusica();
                    if(res3!=null) {
			try {
                            while(res3.next()) {
				TB4.addCell("ID:  "+res3.getString("id"));
                                TB4.addCell("NOME:  "+res3.getString("nome"));
                                TB4.addCell("NOTA:  "+res3.getString("nota"));
                                TB4.addCell("DURAÇÃO:  "+res3.getString("duracao"));
                                TB4.addCell("ALBUM ID:  "+res3.getString("album_id"));
                            }
                            doc.add(TB4);
			} catch (SQLException e) {
				System.out.println("Problema para exibir registros!");
			}
                    } else {
			System.out.println("A pesquisa não retornou nenhum registro!");
                    }
                    } finally {
                        if (doc != null) {
                            doc.close();
                        }
                        if (os != null) {
                            os.close();
                        }
                    }
                   break;
               case 5:
                    System.out.println("SAINDO...");
                   break;
               default:
                    System.out.println("OPÇÂO INVALIDA!");
           }
       }while(opc!=5);
        
    }
    
}
