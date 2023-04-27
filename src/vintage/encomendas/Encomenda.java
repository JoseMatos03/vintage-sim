package vintage.encomendas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import vintage.artigos.Artigo;
import vintage.utils.ui.InfoUtils;
import static vintage.utils.vintage.Utils.getArtigo;

public class Encomenda {

    // Estado da encomenda
    public static final int PENDENTE = 0;
    public static final int EXPEDIDA = 1;
    public static final int FINALIZADA = 2;

    private int codigo;
    private int codigoComprador;
    private List<Integer> artigos;
    private int dimensaoEncomenda;
    private int estadoEncomenda;

    private float precoEncomenda;
    private LocalDateTime dataCriacao;

    public void adicionarArtigos(List<Artigo> artigos, int codigoArtigo) {
        if (this.artigos.size() >= this.dimensaoEncomenda)
            return;

        this.artigos.add(codigoArtigo);
        float novoPrecoEncomenda = this.precoEncomenda + getArtigo(artigos, codigoArtigo).calcularPreco();
        this.setPrecoEncomenda(novoPrecoEncomenda);
    }

    public void removerArtigo(List<Artigo> artigos, int codigoArtigo) {
        if (this.artigos.isEmpty())
            return;

        this.artigos.remove(Integer.valueOf(codigoArtigo));
        float novoPrecoEncomenda = this.precoEncomenda - getArtigo(artigos, codigoArtigo).calcularPreco();
        this.setPrecoEncomenda(novoPrecoEncomenda);
    }

    public float calcularPrecoFinal(List<Artigo> artigos) {
        float precoEncomenda = 0;

        for (Integer codigoArtigo : this.artigos) {
            precoEncomenda += getArtigo(artigos, codigoArtigo).calcularPreco();
        }

        return precoEncomenda;
    }

    public void reembolsar() {
        return;
    }

    public Encomenda(int codigo, int codigoComprador, int dimensaoEncomenda) {
        this.codigo = codigo;
        this.codigoComprador = codigoComprador;
        this.artigos = new ArrayList<>();
        this.dimensaoEncomenda = dimensaoEncomenda;
        this.estadoEncomenda = PENDENTE;
        this.precoEncomenda = 0;
        this.dataCriacao = LocalDateTime.now();
    }

    public List<Integer> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Integer> artigos) {
        this.artigos = artigos;
    }

    public int getDimensaoEncomenda() {
        return dimensaoEncomenda;
    }

    public void setDimensaoEncomenda(int dimensaoEncomenda) {
        this.dimensaoEncomenda = dimensaoEncomenda;
    }

    public int getEstadoEncomenda() {
        return estadoEncomenda;
    }

    public void setEstadoEncomenda(int estadoEncomenda) {
        this.estadoEncomenda = estadoEncomenda;
    }

    public float getPrecoEncomenda() {
        return precoEncomenda;
    }

    public void setPrecoEncomenda(float precoFinal) {
        this.precoEncomenda = precoFinal;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoComprador() {
        return codigoComprador;
    }

    public void setCodigoComprador(int codigoComprador) {
        this.codigoComprador = codigoComprador;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "\n" +
        // artigos
                "Dimensão: " + InfoUtils.parseDimensao(dimensaoEncomenda) + "\n" +
                "Estado: " + InfoUtils.parseEstadoEncomenda(estadoEncomenda) + "\n" +
                "Preço: " + precoEncomenda + "\n" +
                "Data Criação: " + dataCriacao.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

}
