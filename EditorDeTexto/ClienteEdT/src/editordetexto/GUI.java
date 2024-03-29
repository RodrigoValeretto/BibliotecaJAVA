/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editordetexto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Classe correspondente a interface gráfica do editor de texto.
 * @author Rodrigo Augusto Valeretto e Leonardo Cerce Guioto
 */
public class GUI extends JFrame{
    private JFileChooser choose;
    private RepassaMsg server;
    private Thread t;
    private Lock lock = new ReentrantLock();
    private EditorDeTexto ed;
    private String copiado;
    private JPanel painel = new JPanel();
    private JButton redo = new JButton("Refazer");
    private JButton undo = new JButton("Desfazer");
    private JButton insert = new JButton("Inserir Texto");
    private JButton remove = new JButton("Remover Texto");
    private JButton copy = new JButton("Copiar");
    private JButton cut = new JButton("Recortar");
    private JButton paste = new JButton("Colar");
    private JTextArea visor = new JTextArea();
    private JTextArea com = new JTextArea();
    private JScrollPane scroll = new JScrollPane(visor);
    private JScrollPane scroll2 = new JScrollPane(com);
    private JMenuBar barra = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenuItem save = new JMenuItem("Salvar");
    private JMenuItem open = new JMenuItem("Abrir");
    private JMenuItem disconnect = new JMenuItem("Desconectar");
    
    /**
     * Construtor da classe GUI; inicializa as variáveis que definem a interface gráfica do editor, além de definir o que ocorre ao clicar nos botões da mesma.
     * @param n 
     */
    public GUI(EditorDeTexto n)
    {
        super("Editor De Texto");
        this.ed = n;
        this.copiado = "";
        server = new RepassaMsg(true);
        choose = null;
        t = new Thread(server);
        
        painel.setLayout(new GridLayout(1,8));
        
        this.setSize(1280,720);
        this.setLayout(new BorderLayout());

        painel.add(undo);
        painel.add(redo);
        painel.add(insert);
        painel.add(remove);
        painel.add(copy);
        painel.add(cut);
        painel.add(paste);
        painel.add(save);
        painel.add(disconnect);
        
        visor.setEditable(false);
        visor.setLineWrap(true);
        scroll.setBorder(new TitledBorder(new EtchedBorder(), "Texto"));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(640,400));
        
        
        com.setEditable(true);
        com.setLineWrap(true);
        scroll2.setBorder(new TitledBorder(new EtchedBorder(), "Comandos"));
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll2.setPreferredSize(new Dimension(640,320));
        
        barra.add(file);
        file.add(open);
        file.add(save);
        file.add(disconnect);
        
        this.setJMenuBar(barra);
        this.add(painel, BorderLayout.SOUTH);
        this.add(scroll, BorderLayout.CENTER);
        this.add(scroll2, BorderLayout.NORTH);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        undo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a) {
                String str = "";
                lock.lock();
                try
                {
                    ed.desfazer();
                    for(char i : ed.getT().getText())
                        str = str.concat(String.valueOf(i));
                    visor.setText(str);
                }catch(NullPointerException f)
                {
                    System.out.println(f.getMessage());
                }
                finally{lock.unlock();}

            }
        });
        
        redo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a) {
                String str = "";
                lock.lock();
                try
                {
                    ed.refazer();
                    for(char i : ed.getT().getText())
                        str = str.concat(String.valueOf(i));
                    visor.setText(str);
                }catch(NullPointerException f)
                {
                    System.out.println(f.getMessage());
                }finally{lock.unlock();}

            }
        });
        
        insert.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "";
                lock.lock();
                try
                {
                    ed.inseretexto(com.getText());
                    ed.inserealteracao(ed.getDesfaz(),com.getText(), "1");
                    ed.getRefaz().clear();
                }catch(NullPointerException f)
                {
                    System.out.println(f.getMessage());
                }finally{lock.unlock();}

                for(char i : ed.getT().getText())
                    str = str.concat(String.valueOf(i));
                visor.setText(str);
                com.setText("");
            }
        });
       
        remove.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "";
                lock.lock();
                try 
                {
                    if(!com.getText().isEmpty())
                    {   
                        String aux = ed.removetexto(Integer.parseInt(com.getText()));
                        ed.inserealteracao(ed.getDesfaz(),aux, "2");
                        ed.getRefaz().clear();
                    }else{throw new InvalidPropertiesFormatException("\nNenhum valor digitado!\n");}
                }catch(InvalidPropertiesFormatException | NumberFormatException f)
                {
                    System.out.println(f.getMessage());
                }finally{lock.unlock();}
                
                for(char i : ed.getT().getText())
                    str = str.concat(String.valueOf(i));
                visor.setText(str);
                com.setText("");
            }
        });

        
        copy.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                lock.lock();
                try
                {
                    copiar();
                }catch(NullPointerException ex)
                {
                    System.out.println(ex.getMessage());
                }finally{lock.unlock();}

            }
        });
        
        cut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                lock.lock();
                try
                {
                    recortar();
                    ed.getRefaz().clear();
                    ed.getDesfaz().clear();
                }catch(NullPointerException ex)
                {
                    System.out.println(ex.getMessage());
                }finally{lock.unlock();}
            }
        });

        paste.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                lock.lock();
                try
                {
                    colar();
                    ed.getRefaz().clear();
                    ed.getDesfaz().clear();
                }finally{lock.unlock();}
            }
        });

        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!server.isFlag())
                {
                    server = new RepassaMsg(true);
                    t = new Thread(server);
                }
                
                if(!t.isAlive())
                {
                    t.start();
                }
                
                if(choose == null)
                {
                    choose = new JFileChooser();
                    int num = choose.showSaveDialog(save);
                    int i;
                    String str = "";
                
                    if(num == JFileChooser.APPROVE_OPTION)
                    {
                        try {
                            FileReader in = new FileReader(choose.getSelectedFile());
                            ed.getT().getText().clear();
                            i = in.read();
                            while(i != -1)
                            {
                                str = str.concat(String.valueOf((char)i));
                                i = in.read();
                            }
                            ed.inseretexto(str);
                            visor.setText(str);
                        }
                        catch (FileNotFoundException ex) {}
                        catch (IOException ex) {}
                        
                        server.setNome(choose.getSelectedFile().getName());
                        server.setTxt(visor.getText());
                        t.interrupt();
                        com.setText("");                
                    }
                }
                else
                {
                    server.setNome(choose.getSelectedFile().getName());
                    server.setTxt(visor.getText());
                    t.interrupt();
                    com.setText("");                                    
                }
            }
        });
        
        disconnect.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                server.setFlag(false);
                t.interrupt();
                ed.getT().getText().clear();
                ed.getDesfaz().clear();
                ed.getRefaz().clear();
                copiado = "";
                visor.setText("");
                com.setText("");
                choose = null;
            }
        });
        
        open.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                choose = new JFileChooser();
                int num = choose.showOpenDialog(open);
                int i;
                String str = "";
                
                if(num == JFileChooser.APPROVE_OPTION)
                {
                    try {
                        FileReader in = new FileReader(choose.getSelectedFile());
                        ed.getT().getText().clear();
                        //ARRUMAR ISSO AQUI AINDA FALTANDO UMA RESOLUÇÃO DESSE PROBLEMA
                        i = in.read();
                        while(i != -1)
                        {
                            str = str.concat(String.valueOf((char)i));
                            i = in.read();
                        }
                        ed.inseretexto(str);
                        visor.setText(str);
                    }
                    catch (FileNotFoundException ex) {}
                    catch (IOException ex) {}
                }
            }
        });
    }
    
    /**
     * Copia um texto selecionado no visor para uma variável da classe GUI.
     * @throws NullPointerException
     */
    public void copiar(){
        if(visor.getSelectedText() == null)
            throw new NullPointerException("Nenhum Texto Selecionado");

        this.copiado = visor.getSelectedText();   
        visor.select(0, 0);
    }
    
    /**
     * Recorta um texto selecionado no visor para uma variável da classe GUI.
     * @throws NullPointerException
     */
    public void recortar(){
        if(visor.getSelectedText() == null)
            throw new NullPointerException("Nenhum Texto Selecionado");
        
        String str = "";
        this.copiado = visor.getSelectedText();
        int ini = visor.getSelectionStart();
        int fim = visor.getSelectionEnd();
        
        for(int i = ini; i < fim; i++)
            this.ed.getT().getText().remove(ini);
        
        for(char i : this.ed.getT().getText())
            str = str.concat(String.valueOf(i));
            
        visor.setText("");
        visor.setText(str);
        visor.select(0, 0);
    }
    
    /**
     * Cola uma string salva na área de transferência no fim do texto.
     */
    public void colar(){
        String str = this.visor.getText();
        
        str = str.concat(this.copiado);
        
        ed.inseretexto(this.copiado);
        
        visor.setText("");
        visor.setText(str);
    }
}
