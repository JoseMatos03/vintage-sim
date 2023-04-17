package vintage.utilizadores;

import vintage.artigos.Artigo;

import java.util.ArrayList;
import java.util.List;

public class Utilizador {

    private int codigo; // TODO Atribuir codigo de forma automática

    private String email;

    private String nome;

    private String morada;

    private int numeroFiscal;

    //TODO adicionar e remover artigos nas listas
    private List<Artigo> listados;
    private List<Artigo> vendidos;
    private List<Artigo> comprados;

    //TODO quando remove do listado para o vendido, também sobe o valorEmVendas
    private float valorEmVendas;


    public Utilizador(int codigo, String email, String nome, String morada, int numero_fiscal, float valorEmVendas)
    {
        this.codigo = codigo;
        this.email  = email;
        this.nome = nome;
        this.morada = morada;
        this.numeroFiscal = numero_fiscal;
        this.listados = new ArrayList<>();
        this.vendidos = new ArrayList<>();
        this.comprados = new ArrayList<>();
        this.valorEmVendas = valorEmVendas;
    }

    public int getCodigo(){
        return codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada(){
        return morada;
    }

    public List<Artigo> getListados() {
        return listados;
    }

    public List<Artigo> getVendidos() {
        return vendidos;
    }

    public List<Artigo> getComprados() {
        return comprados;
    }

    public void setListados(List<Artigo> listados) {
        this.listados = listados;
    }

    public void setVendidos(List<Artigo> vendidos) {
        this.vendidos = vendidos;
    }

    public void setComprados(List<Artigo> comprados) {
        this.comprados = comprados;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNumeroFiscal(){
        return numeroFiscal;
    }

    public void setNumeroFiscal(int numeroFiscal) {
        this.numeroFiscal = numeroFiscal;
    }

    public float getValorEmVendas() {
        return valorEmVendas;
    }

    public void setValorEmVendas(float valorEmVendas) {
        this.valorEmVendas = valorEmVendas;
    }
}
