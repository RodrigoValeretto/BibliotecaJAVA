/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.IOException;


import java.util.LinkedList;
import java.util.ListIterator;

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
        
/*    public int buscaUser(String x)
    {
        if(this.UserList == null)
            return -1;
        
        int i = 0;
        Usuario aux;
        ListIterator<Usuario> u = this.UserList.listIterator();
        
        for (i = 0; i<this.UserList.size(); i++)
        {
            if(x.compareTo(u.getNusp) == 0)
                return i;
            i++;
        }
        return -1;
    }
*/
    public boolean InsereUser()
    {
        if(this.UserList == null)
            return false;
        
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
            this.UserList.add(user);
        
        return true;
    }

    public boolean InsereMidia()
    {
        if(this.MidiaList == null)
            return false;
        
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
        
        
        if(!bib.InsereUser())
            return;
        int i = bib.buscaUser(scan.next());
        bib.UserList.get(i);
        
    }

}
