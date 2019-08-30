/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.IOException;
/**
 *
 * @author rodrigo
 */
public class Usuario {
    private String nome;
    private String nusp;
    private Data d;
    private Endereco e;
    
    public Usuario()
    {
        this.nome = null;
        this.nusp = null;
        this.d = null;
        this.e = null;
    }

    public Usuario(String nome, String nusp, Data d, Endereco e) {
        this.nome = nome;
        this.nusp = nusp;
        this.d = d;
        this.e = e;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNusp() {
        return nusp;
    }

    public void setNusp(String nusp) {
        this.nusp = nusp;
    }

    public Data getD() {
        return d;
    }

    public void setD(Data d) {
        this.d = d;
    }

    public Endereco getE() {
        return e;
    }

    public void setE(Endereco e) {
        this.e = e;
    }
    
    public int verificacampos()
    {
        try{
        if(this.nome == null || this.d == null || this.e == null || this.nusp == null)
            return 0;
        }catch(InvalidValueException e)
        {
            
        }
        return 1;
    }
    
    public int verificanusp()
    {
        if(this.nusp.length() != 7)
            return 0;
        return 1;
    }
    
    public int verificadata()
    {
        if(this.d.getDia() < 1 && this.d.getDia() > 31)
            return 0;
        if(this.d.getMes() < 1 && this.d.getMes() > 12)
            return 0;
        
        return 1;
    }
}
