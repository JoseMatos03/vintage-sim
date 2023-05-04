package vintage.artigos.sapatilhas;

import vintage.artigos.Artigo;
import vintage.transportadoras.Transportadora;
import vintage.utils.artigos.Utils;
import vintage.utils.ui.InfoUtils;

public class Sapatilhas extends Artigo {

    // Tipos de atacadores
    public static final int ATACADORES = 0;
    public static final int ATILHOS = 1;

    private int tamanho;
    private int atacadores;
    private String cor;
    private int anoColecao;

    public Sapatilhas(int tipo, float estadoUtilizacao, int numDonos, String descricao, String marca, int codigo,
            float precoBase, int tamanho, int atacadores, String cor, int anoColecao, int codigoVendedor,
            Transportadora transportadora, boolean premiumEstado) {
        super(tipo, estadoUtilizacao, numDonos, descricao, marca, codigo, precoBase, codigoVendedor, transportadora, premiumEstado);

        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.anoColecao = anoColecao;
    }

    @Override
    public float calcularPreco() {
        float precoFinal = this.getPrecoBase() + this.calcularCorrecaoPremium(this.getAnoColecao()) + this.calcularCorrecao();
        return precoFinal;
    }

    @Override
    public float calcularCorrecao() {
        float correcao = 0;

        correcao += Utils.calcularPercentagem(this.getPrecoBase(), this.getTransportadora().getValorExpedicao());
        if (this.getEstadoUtilizacao() != 1f) {
            correcao -= this.getPrecoBase() * this.getEstadoUtilizacao();
        }
        if (this.tamanho > 45) {
            correcao -= this.getPrecoBase() * 0.25;
        }

        return correcao;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getAtacadores() {
        return atacadores;
    }

    public void setAtacadores(int atacadores) {
        this.atacadores = atacadores;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAnoColecao() {
        return anoColecao;
    }

    public void setAnoColecao(int anoColecao) {
        this.anoColecao = anoColecao;
    }

    @Override
    public String toString() {
        String tipo = InfoUtils.parseTipoArtigo(this.getTipo());
        String estado = InfoUtils.parseEstadoUtilizacao(this.getEstadoUtilizacao());
        String premium = InfoUtils.parsePremium(this.getPremiumEstado());

        return "Código: " + this.getCodigo() + "\n" +
                "Código Vendedor: " + this.getCodigoVendedor() + "\n" +
                "Tipo: " + tipo + "\n" +
                "Marca: " + this.getMarca() + "\n" +
                "Descrição: " + this.getDescricao() + "\n" +
                "Ano coleção: " + this.getAnoColecao() + "\n" +
                "Nº Donos: " + this.getNumDonos() + "\n" +
                "Estado: " + estado + "\n" +
                "Preço Base: " + Utils.arrondarCentesimas(this.getPrecoBase()) + "\n" +
                "Preço Final: " + Utils.arrondarCentesimas(this.calcularPreco()) + "\n" +
                "Tamanho: " + this.getTamanho() + "\n" +
                "Tipo Atacadores: " + InfoUtils.parseTipoAtacadores(this.getAtacadores()) + "\n" +
                "Cor: " + this.getCor() + "\n" +
                "Transportadora: " + this.getTransportadora().getNome() + "\n" +
                "Premium: " + premium;
    }

}
