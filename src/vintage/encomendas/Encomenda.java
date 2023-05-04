package vintage.encomendas;

import static vintage.utils.vintage.Utils.getArtigo;
import static vintage.utils.vintage.Utils.getEncomendaOfArtigo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import vintage.artigos.Artigo;
import vintage.utils.ErrorCode;
import vintage.utils.artigos.Utils;
import vintage.utils.ui.InfoUtils;

public class Encomenda {

    // Estado da encomenda
    public static final int PENDENTE = 0;
    public static final int EXPEDIDA = 1;
    public static final int FINALIZADA = 2;

    public static final int DIAS_REEMBOLSO = 3;

    private int codigo;
    private int codigoComprador;
    private List<Integer> artigos;
    private int dimensaoEncomenda;
    private int estadoEncomenda;

    private float precoEncomenda;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEntrega;

    public Encomenda(int codigo, int codigoComprador, int dimensaoEncomenda) {
        this.codigo = codigo;
        this.codigoComprador = codigoComprador;
        this.artigos = new ArrayList<>();
        this.dimensaoEncomenda = dimensaoEncomenda;
        this.estadoEncomenda = PENDENTE;
        this.precoEncomenda = 0;
        this.dataCriacao = LocalDateTime.now();
        this.dataEntrega = null;
    }

    public ErrorCode adicionarArtigo(List<Artigo> artigos, List<Encomenda> encomendas, int codigoArtigo) {
        if (estadoEncomenda != PENDENTE)
            return ErrorCode.EM_EXPEDICAO;

        if (this.artigos.size() >= this.dimensaoEncomenda)
            return ErrorCode.SEM_ESPACO;

        if (getArtigo(artigos, codigoArtigo) == null)
            return ErrorCode.CODIGO_INVALIDO;

        Artigo artigo = getArtigo(artigos, codigoArtigo);
        if (artigo.getCodigoVendedor() == codigoComprador)
            return ErrorCode.ARTIGO_DO_COMPRADOR;

        if (getEncomendaOfArtigo(encomendas, artigo) != -1)
            return ErrorCode.EM_ENCOMENDA;

        this.artigos.add(codigoArtigo);
        float novoPrecoEncomenda = this.precoEncomenda + getArtigo(artigos, codigoArtigo).calcularPreco();
        this.setPrecoEncomenda(novoPrecoEncomenda);

        return ErrorCode.NO_ERRORS;
    }

    public ErrorCode removerArtigo(List<Artigo> artigos, int codigoArtigo) {
        if (estadoEncomenda != PENDENTE)
            return ErrorCode.EM_EXPEDICAO;

        if (this.artigos.isEmpty())
            return ErrorCode.ENCOMENDA_VAZIA;

        if (getArtigo(artigos, codigoArtigo) == null)
            return ErrorCode.CODIGO_INVALIDO;

        if (!this.artigos.contains(Integer.valueOf(codigoArtigo)))
            return ErrorCode.ARTIGO_INVALIDO;

        this.artigos.remove(Integer.valueOf(codigoArtigo));
        float novoPrecoEncomenda = this.precoEncomenda - getArtigo(artigos, codigoArtigo).calcularPreco();
        this.setPrecoEncomenda(novoPrecoEncomenda);

        return ErrorCode.NO_ERRORS;
    }

    public float calcularPrecoFinal(List<Artigo> artigos) {
        float precoEncomenda = 0;

        for (Integer codigoArtigo : this.artigos) {
            precoEncomenda += getArtigo(artigos, codigoArtigo).calcularPreco();
        }

        return precoEncomenda;
    }

    public void expedir() {

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

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    @Override
    public String toString() {
        if (dataEntrega == null) {
            return "Código: " + codigo + "\n" +
                    "Código Comprador: " + codigoComprador + "\n" +
                    "Artigos: " + artigos.toString() + "\n" +
                    "Dimensão: " + InfoUtils.parseDimensao(dimensaoEncomenda) + "\n" +
                    "Estado: " + InfoUtils.parseEstadoEncomenda(estadoEncomenda) + "\n" +
                    "Preço: " + precoEncomenda + "\n" +
                    "Data Criação: " + dataCriacao.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n" +
                    "Data Entrega: " + "Por expedir.";
        }
        return "Código: " + codigo + "\n" +
                "Código Comprador: " + codigoComprador + "\n" +
                "Artigos: " + artigos.toString() + "\n" +
                "Dimensão: " + InfoUtils.parseDimensao(dimensaoEncomenda) + "\n" +
                "Estado: " + InfoUtils.parseEstadoEncomenda(estadoEncomenda) + "\n" +
                "Preço: " + Utils.arrondarCentesimas(precoEncomenda) + "\n" +
                "Data Criação: " + dataCriacao.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n" +
                "Data Entrega: " + dataEntrega.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

}
