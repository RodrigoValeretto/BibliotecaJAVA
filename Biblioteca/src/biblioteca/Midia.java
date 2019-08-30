/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author rodrigo
 */
public class Midia {
    private String titulo;
    private String autor;
    private int anopub;
    private String tipo;
    private Editora ed;
    private int andar;
    private String sec;
    private boolean disp;

    public Midia(String titulo, String autor, int anopub, String tipo, Editora ed, int andar, String sec) {
        this.titulo = titulo;
        this.autor = autor;
        this.anopub = anopub;
        this.tipo = tipo;
        this.ed = ed;
        this.andar = andar;
        this.sec = sec;
        this.disp = true;
    }

    
    public Midia() {
        this.titulo = null;
        this.autor = null;
        this.anopub = -1;
        this.tipo = null;
        this.ed = null;
        this.andar = -1;
        this.sec = null;
        this.disp = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnopub() {
        return anopub;
    }

    public void setAnopub(int anopub) {
        this.anopub = anopub;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Editora getEd() {
        return ed;
    }

    public void setEd(Editora ed) {
        this.ed = ed;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    @Override
    public String toString() {
        return "Midia{" + "titulo=" + titulo + ", autor=" + autor + ", anopub=" + anopub + ", tipo=" + tipo + ", ed=" + ed + ", andar=" + andar + ", sec=" + sec + '}';
    }
    
    public boolean iniciaMidia() throws IOException
    {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Digite o título da mídia:");
        this.setTitulo(scan.nextLine());
        if(this.titulo.isEmpty() || this.titulo == null)
        {
            throw new IOException("Entrada inválida!");
        }
        
        System.out.println("Digite o nome do autor da mídia:");
        this.setAutor(scan.nextLine());
        if(this.autor.isEmpty() || this.autor == null)
        {
            throw new IOException("Entrada inválida!");
        }
                
        System.out.println("Digite o tipo da mídia:");
        this.setTipo(scan.nextLine());
        if(this.tipo.isEmpty() || this.tipo == null)
        {
            throw new IOException("Entrada inválida!");
        }

        System.out.println("Digite a seção à qual a mídia pertence:");
        this.setSec(scan.nextLine());
        if(this.sec.isEmpty() || this.sec == null)
        {
            throw new IOException("Entrada inválida!");
        }

        Editora edit = new Editora();
        System.out.println("Digite o nome da editora:");
        edit.setNome(scan.nextLine());
        if(edit.getNome().isEmpty())
        {
            throw new IOException("Entrada inválida!");
        }

        
        System.out.println("Digite o telefone da editora:");
        edit.setTel(scan.nextLine());
        if(edit.getTel().isEmpty())
        {
            throw new IOException("Entrada inválida!");
        }

        
        System.out.println("Digite o email da editora:");
        edit.setEmail(scan.nextLine());
        if(edit.getEmail().isEmpty())
        {
            throw new IOException("Entrada inválida!");
        }
       
        this.setEd(edit);
        
        System.out.println("Digite o andar em que a mídia se encontra:");
        this.setAndar(scan.nextInt());
        if(this.andar < 1 || this.andar > 3)
        {
            throw new IOException("Entrada inválida!");
        }
        
        System.out.println("Digite o ano de publicação da mídia:");
        this.setAnopub(scan.nextInt());
        
        return true;
    }
}
