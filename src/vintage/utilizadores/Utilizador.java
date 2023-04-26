package vintage.utilizadores;

import vintage.artigos.Artigo;

import java.util.ArrayList;
import java.util.List;

public class Utilizador {

    // Flags
    // TODO impedir de fazer ações caso esteja inativa
    public static final int ATIVA = 0;
    public static final int INATIVA = 1;

    private int codigo;
    private String email;
    private String nome;
    private String morada;
    private int numeroFiscal;
    private int atividade;

    private List<Integer> listados;
    private List<Artigo> vendidos;
    private List<Artigo> comprados;
    private float valorEmVendas;

    public void criarListagem(Artigo artigo) {
        if(this.atividade == INATIVA)
            return;
        int codigo = artigo.getCodigo();
        if (this.listados.contains(codigo))
            return;

        this.listados.add(artigo.getCodigo());
    }

    public void removerListagem(Artigo artigo){
        if(this.atividade == INATIVA)
            return;
        int codigo = artigo.getCodigo();
        if (!this.listados.contains(codigo)) {
            return;
        }

        this.listados.remove(codigo);
    }

    public void venderArtigo(Artigo artigo) {
        if(this.atividade == INATIVA)
            return;
        int codigo = artigo.getCodigo();
        if (!this.listados.contains(codigo))
            return;
        
        this.vendidos.add(artigo);
        this.listados.remove(codigo);
        this.valorEmVendas += artigo.calcularPreco();
    }

    public void comprarArtigo(Artigo artigo, Utilizador utilizador) {
        if(this.atividade == INATIVA)
            return;
        int codigo = artigo.getCodigo();
        if (!utilizador.getListados().contains(codigo))
            return;
        
        utilizador.venderArtigo(artigo);
        this.comprados.add(artigo);
    }

    public Utilizador(int codigo, String email, String nome, String morada, int numeroFiscal) {
        this.codigo = codigo;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.numeroFiscal = numeroFiscal;

        this.listados = new ArrayList<>();
        this.vendidos = new ArrayList<>();
        this.comprados = new ArrayList<>();
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

    public List<Integer> getListados() {
        return listados;
    }

    public void setListados(List<Integer> listados) {
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

    public int getAtividade() {
        return atividade;
    }

    public void setAtividade(int atividade) {
        this.atividade = atividade;
    }

}
