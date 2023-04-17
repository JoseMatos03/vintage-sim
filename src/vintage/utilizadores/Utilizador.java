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

    // TODO adicionar e remover artigos nas listas
    // Listas de artigos associados ao utilizador
    private List<Artigo> listados;
    private List<Artigo> vendidos;
    private List<Artigo> comprados;

    private float valorEmVendas;

    public Utilizador(int codigo, String email, String nome, String morada, int numeroFiscal) {
        this.codigo = codigo;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.numeroFiscal = numeroFiscal;

        this.listados = new ArrayList<Artigo>();
        this.vendidos = new ArrayList<Artigo>();
        this.comprados = new ArrayList<Artigo>();
        this.valorEmVendas = 0;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNumeroFiscal() {
        return numeroFiscal;
    }

    public void setNumeroFiscal(int numeroFiscal) {
        this.numeroFiscal = numeroFiscal;
    }

    public List<Artigo> getListados() {
        return listados;
    }

    public void setListados(List<Artigo> listados) {
        this.listados = listados;
    }

    public List<Artigo> getVendidos() {
        return vendidos;
    }

    public void setVendidos(List<Artigo> vendidos) {
        this.vendidos = vendidos;
    }

    public List<Artigo> getComprados() {
        return comprados;
    }

    public void setComprados(List<Artigo> comprados) {
        this.comprados = comprados;
    }

    public float getValorEmVendas() {
        return valorEmVendas;
    }

    public void setValorEmVendas(float valorEmVendas) {
        this.valorEmVendas = valorEmVendas;
    }

}
