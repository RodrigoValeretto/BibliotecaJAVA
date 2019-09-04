/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.IOException;

import java.util.LinkedList;

import java.util.Scanner;

/**
 *
 * @author rodrigo
 */
public class Usuario {
    private String nome;
    private String nusp;
    private Data d;
    private Endereco e;
    private LinkedList<Midia> ret;
    
    public Usuario()
    {
        this.nome = null;
        this.nusp = null;
        this.d = null;
        this.e = null;
        this.ret = new LinkedList();
    }

    public Usuario(String nome, String nusp, Data d, Endereco e) {
        this.nome = nome;
        this.nusp = nusp;
        this.d = d;
        this.e = e;
        this.ret = new LinkedList();
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
    
    public void setData(String str)
    {
        String[] S;
        S = str.split("/");
        Data r = new Data(Integer.parseInt(S[0]), Integer.parseInt(S[1]), Integer.parseInt(S[2]));
        this.setD(r);
    }        
    public Endereco getE() {
        return e;
    }

    public void setE(Endereco e) {
        this.e = e;
    }

    public LinkedList<Midia> getRet() {
        return ret;
    }

    public void setRet(LinkedList<Midia> ret) {
        this.ret = ret;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", nusp=" + nusp + ", d=" + d + ", e=" + e + '}';
    }
    
    public void verificacampos() throws IOException
    {
        if(this.nome == null || this.d == null || this.e == null || this.nusp == null)
            throw new IOException("Usuário não inicializado!");
    }
    
    public void verificanome() throws IllegalArgumentException
    {
        if(this.nome.isEmpty())
            throw new IllegalArgumentException("Nome inválido!");
    }
    
    public void verificanusp() throws IllegalArgumentException
    {
        if(this.nusp.length() != 8)
            throw new IllegalArgumentException("Tamanho de NUSP inválido!");
    }
    
    public void verificadata() throws IllegalArgumentException
    {
        if(this.d.getDia() < 1 || this.d.getDia() > 31)
            throw new IllegalArgumentException("Data inválida!");
        if(this.d.getMes() < 1 || this.d.getMes() > 12)
            throw new IllegalArgumentException("Data inválida!");
    }
    
    public void verificaend() throws IllegalArgumentException
    {
        if(this.e.getRua().isEmpty() || this.e.getCidade().isEmpty())
            throw new IllegalArgumentException("Endereço inválido!");
    }
    
    public boolean IniciaUser()
    {
        Endereco x = new Endereco();
        
        System.out.println("Digite o nome do usuário:");
        Scanner scan = new Scanner(System.in);
        try
        {
            this.setNome(scan.nextLine());
            this.verificanome();
        }catch(IllegalArgumentException p)
        {
            System.out.println(p.getMessage());
            return false;
        }
        
        try
        {
            System.out.println("Digite o NUSP do usuário:");
            this.setNusp(scan.nextLine());
            this.verificanusp();
        }catch(IllegalArgumentException p)
        {
            System.out.println(p.getMessage());
            return false;
        }
        
        try
        {
            System.out.println("Digite a data de nascimento do usuário no formato dia/mes/ano:");
            this.setData(scan.nextLine());
            this.verificadata();
        }catch(IllegalArgumentException p)
        {
            System.out.println(p.getMessage());
            return false;
        }
        
        System.out.println("Sobre o endereço do usuário digite:");
        
        System.out.println("A Rua:");
        x.setRua(scan.nextLine());
        
        System.out.println("A cidade:");
        x.setCidade(scan.nextLine());
        
        System.out.println("O número:");
        x.setNum(scan.nextInt());
        
        try
        {
            this.setE(x);
            this.verificaend();
        }catch(IllegalArgumentException p)
        {
            System.out.println(p.getMessage());
            return false;
        }
            
        System.out.println("Usuário inserido com sucesso!");        
        return true;
    }
}
