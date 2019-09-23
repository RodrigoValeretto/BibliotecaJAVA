/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editordetexto;

import java.util.Scanner;

/**
 *
 * @author rodrigo
 */
public class EditorDeTexto {
    private Texto t;
    
    public void exibetexto()
    {
        for(char l : this.t.getText())
        {
            System.out.print(l);
        }
        
        System.out.print("\n");
    }
    
    public void removetexto()
    {
        Scanner scan = new Scanner(System.in);
        int num;
        
        System.out.println("Quantos caracteres deseja remover:");
        num = scan.nextInt();
        
        for(int i = 0; i < num; i++)
        {
            this.t.getText().removeLast();
        }
        
        
    }
    
    public void inseretexto()
    {
        Scanner scan = new Scanner(System.in);
        String str;
        int i;
        
        str = scan.nextLine();
            
        for(i = 0; i < str.length(); i++)
        {
            this.t.getText().add(str.charAt(i));
        }
    }

    public EditorDeTexto() {
        this.t = new Texto();
    }
    
    /**
     * @param args the command line arguments
     */    
    public static void main(String[] args) {
        EditorDeTexto e = new EditorDeTexto();
        Scanner scan = new Scanner(System.in);
        
        while(true)
        {
            System.out.println("---------------------------------------------");
            System.out.println("Selecione a opção desejada:");
            System.out.println("0 - Sair do programa");
            System.out.println("1 - Insere Texto");
            System.out.println("2 - Exclui texto");
            System.out.println("3 - Exibe Texto");
            System.out.println("---------------------------------------------");
            
            switch(scan.nextLine())
            {
                case "0":
                    return;
                
                case "1":
                    e.inseretexto();
                    break;
                
                case "2":
                    e.removetexto();
                    break;
                    
                case "3":
                    e.exibetexto();
                    break;
                    
                    
            }
        }
        
        
    }
    
}
