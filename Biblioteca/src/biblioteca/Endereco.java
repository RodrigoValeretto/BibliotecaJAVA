/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

/**
 *
 * @author rodrigo
 */
public class Endereco {
    private String rua;
    private String cidade;
    private int num;
    
    public Endereco(String rua, String bairro, String cidade, int num)
    {
        this.rua = rua;
        this.cidade = cidade;
        this.num = num;
    }

    public Endereco() {
        this.rua = null;
        this.cidade = null;
        this.num = -1;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    public String toString()
    {
        return ("Endereço: " + this.rua + ", " + this.num + ", " + this.cidade);
    }
    
}
