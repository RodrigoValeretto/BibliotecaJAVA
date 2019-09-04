/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.IOException;

import java.util.EmptyStackException;

import java.util.LinkedList;

import java.util.Scanner;

/**
 *
 * @author rodrigo
 */
public class Biblioteca {
    private LinkedList <Usuario> UserList;
    private LinkedList <Midia>  MidiaList;
    
    public Biblioteca()
    {
        this.UserList = new LinkedList();
        this.MidiaList = new LinkedList();
    }
       
    
    public void calculaDivida(Midia m)
    {
        if(m.getNdias() > 7)
        System.out.println("O usuário deve pagar " + (m.getNdias() - 7) + " reais para quitar sua dívida!");
    }
    
    public void incrementaDia() throws EmptyStackException
    {
        if(this.MidiaList.isEmpty())
            throw new EmptyStackException();
        
        for(Midia m : this.MidiaList)
            if(m.getNdias() != -1)
                m.setNdias(m.getNdias() + 1);
    }
    
    public void Emprestimo() throws NullPointerException
    {
        Midia m;
        Usuario u;
        String X;
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Deseja buscar a mídia por titulo (1) ou por autor (0):");
        if(scan.nextLine().equals("1"))
        {
            System.out.println("Digite o título da mídia:");
            X = scan.nextLine();
            m = this.buscaMidiaT(X);
            if(m == null)
            {
                throw new NullPointerException("Mídia não encontrada ou operação cancelada!");
            }
        }
        else
        {
            System.out.println("Digite o autor da mídia:");
            X = scan.nextLine();
            m = this.buscaMidiaA(X);
            if(m == null)
            {
                throw new NullPointerException("Mídia não encontrada ou operação cancelada!");
            }
        }
        
        if(m.getNdias() == -1)
            System.out.println("Mídia Disponível");
        else
        {
            System.out.println("Mídia Indisponível");
            return;
        }
        System.out.println("Digite o Numero USP do usuário:");
        X = scan.nextLine();
        u = this.buscaUser(X);
        if(u == null)
        {
                throw new NullPointerException("Usuário não encontrado!");
        }
        
        if(u.getRet() == null)
        {
                throw new NullPointerException("Erro na inicialização da lista!");
        }
        
        for(Midia aux : u.getRet())
        {
            if(aux.getNdias() > 7)
            {
                System.out.println("Usuário não apto à retirada");
                return;
            }
        }
        
        if(u.getRet().isEmpty())
        {
            u.getRet().addFirst(m);
            m.setNdias(0);
            System.out.println("Empréstimo realizado com sucesso!");
        }
        else
        {
            u.getRet().add(m);
            m.setNdias(0);
            System.out.println("Empréstimo realizado com sucesso!");

        }       
    }
    
    public void Devolucao() throws NullPointerException
    {
        int i = 0;
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Digite o NUSP do usuário que deseja fazer uma devolução:");
        
        Usuario u = this.buscaUser(scan.nextLine());
        if(u == null)
            throw new NullPointerException("Usuário não encontrado!");
        
        System.out.println("Midias retiradas pelo usuário:");
        if(u.getRet().isEmpty())
        {
            System.out.println("Não há midias para serem devolvidas pelo usuário");
            return;
        }
            
        for(Midia m : u.getRet())
        {
            System.out.println("Indice = " + i + "   Título : " + m.getTitulo() + "   Tempo desde a retirada : " + m.getNdias() + " dias");
            i++;
        }
        
        System.out.println("Digite o índice da mídia que deseja devolver ou -1 caso não queira devolver nenhuma:");
        i = scan.nextInt();
        if(i != -1)
        {
            Midia aux = u.getRet().remove(i);
            this.calculaDivida(aux);
            aux.setNdias(-1);
            System.out.println("Mídia devolvida com sucesso!");
            
        }
    }
    
    public Usuario buscaUser(String x)
    {
        if(this.UserList == null || this.UserList.isEmpty())
            return null;
        
        if(!x.isEmpty())
            for(Usuario user : this.UserList)
                if(user.getNusp().equals(x))
                    return user;
        
        return null;
    }
    
    public Midia buscaMidiaT(String x)
    {
        if(this.MidiaList == null || this.MidiaList.isEmpty())
            return null;
        
        int i = 0;
        Scanner scan = new Scanner(System.in);
        
        if(!x.isEmpty())
        {
            for(Midia m : this.MidiaList)
            {
                if(m.getTitulo().contains(x))
                {
                    System.out.print("Indice = " + i);
                    System.out.print("     Titulo : " + m.getTitulo());
                    System.out.print("     Autor : " + m.getAutor());
                    System.out.print("     Ano de Publicação : " + m.getAnopub());
                    System.out.print("     Tipo : " + m.getTipo());
                    System.out.print("     Seção : " + m.getSec());
                    System.out.println("     Andar : " + m.getAndar());
                }

                i++;
            }

            System.out.println("Digite o indice da obra desejada ou -1 se não quiser nenhuma:");
            i = scan.nextInt();
            if(i == -1)
                return null;
            else
                return this.MidiaList.get(i);
        }
        
        return null;
    }

    public Midia buscaMidiaA(String x)
    {
        if(this.MidiaList == null || this.MidiaList.isEmpty())
            return null;
        
        int i = 0;
        Scanner scan = new Scanner(System.in);
        
        if(!x.isEmpty())
        {
            for(Midia m : this.MidiaList)
            {
                if(m.getAutor().contains(x))
                {
                    System.out.print("Indice = " + i);
                    System.out.print("     Titulo : " + m.getTitulo());
                    System.out.print("     Autor : " + m.getAutor());
                    System.out.print("     Ano de Publicação : " + m.getAnopub());
                    System.out.print("     Tipo : " + m.getTipo());
                    System.out.print("     Seção : " + m.getSec());
                    System.out.println("     Andar : " + m.getAndar());
                }

                i++;
            }

            System.out.println("Digite o indice da obra desejada ou -1 se não quiser nenhuma:");
            i = scan.nextInt();
            if(i == -1)
                return null;
            else
                return this.MidiaList.get(i);
        }
        
        return null;
    }

    public boolean InsereUser()
    {
        if(this.UserList == null)
            throw new NullPointerException("Lista de usuários não inicializada!");
        
        Usuario user = new Usuario();
        if(user.IniciaUser())
            try
            {
                user.verificacampos();
            }catch(IOException e)
            {
                System.out.println(e.getMessage());
                return false;
            }
        else
            return false;

        if(this.UserList.isEmpty())
            this.UserList.addFirst(user);
        else
            if(this.buscaUser(user.getNusp()) == null)
                this.UserList.add(user);
            else
                return false;
        
        return true;
    }

    public boolean InsereMidia()
    {
        if(this.MidiaList == null)
            throw new NullPointerException("Lista de mídias não inicializada!");
        
        Midia mid = new Midia();
        try
        {
            mid.iniciaMidia();
        }catch(IOException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        
        if(this.MidiaList.isEmpty())
            this.MidiaList.addFirst(mid);
        else
            this.MidiaList.add(mid);
            
        return true;
    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        // TODO code application logic here
        Biblioteca bib = new Biblioteca();
        Scanner scan = new Scanner(System.in);

        while(true)
        {
            System.out.println("Escolha uma das opções abaixo:");
            System.out.println("0 - Sair   1 - Inserir usuário   2 - Inserir mídia   3 - Realizar empréstimo   4 - Realizar devolução   5 - Incrementa dia");
            switch(scan.nextLine())
            {
                case "0":
                    return;

                case "1":
                    try
                    {
                        if(!bib.InsereUser())
                            System.out.println("Erro na operação, tente novamente!");
                    }catch(NullPointerException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case "2":
                    try
                    {
                        if(!bib.InsereMidia())
                            System.out.println("Erro na operação, tente novamente!");
                    }catch(NullPointerException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case "3":
                    try
                    {
                        bib.Emprestimo();
                    }catch(NullPointerException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    try
                    {
                        bib.Devolucao();
                    }catch(NullPointerException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case "5":
                    try
                    {
                        bib.incrementaDia();
                    }catch(EmptyStackException e)
                    {
                       System.out.println("Lista de mídias vazia!");
                    }
                    break;
            }
        }        
    }

}
