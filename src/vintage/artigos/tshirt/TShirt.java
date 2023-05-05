package vintage.artigos.tshirt;

import vintage.artigos.Artigo;
import vintage.transportadoras.Transportadora;
import vintage.utils.artigos.Utils;
import vintage.utils.ui.InfoUtils;

public class TShirt extends Artigo {

    // Tipos de padrão
    public static final int LISO = 0;
    public static final int RISCOS = 1;
    public static final int PALMEIRAS = 2;

    // Tamanhos
    public static final String S = "S";
    public static final String M = "M";
    public static final String L = "L";
    public static final String XL = "XL";

    private String tamanho;
    private int padrao;

    public TShirt(int tipo, float estadoUtilizacao, int numDonos, String descricao, String marca, int codigo,
            float precoBase,
            String tamanho, int padrao, int codigoVendedor, Transportadora transportadora) {
        super(tipo, estadoUtilizacao, numDonos, descricao, marca, codigo, precoBase, codigoVendedor, transportadora, false);

        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    @Override
    public float calcularPreco() {
        float precoFinal = this.getPrecoBase() + this.calcularCorrecao();
        return precoFinal;
    }

    @Override
    public float calcularCorrecao() {
        float correcao = 0;

        correcao += Utils.calcularPercentagem(this.getPrecoBase(), this.getTransportadora().getValorExpedicao());
        if (this.padrao == LISO) {
            return correcao;
        }
        if (this.getEstadoUtilizacao() != 1f) {
            correcao -= this.getPrecoBase() * 0.5f;
        }

        return correcao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getPadrao() {
        return padrao;
    }

    public void setPadrao(int padrao) {
        this.padrao = padrao;
    }

    @Override
    public String toString() {
        String tipo = InfoUtils.parseTipoArtigo(this.getTipo());
        String estado = InfoUtils.parseEstadoUtilizacao(this.getEstadoUtilizacao());

        return "Código: " + this.getCodigo() + "\n" +
                "Código Vendedor: " + this.getCodigoVendedor() + "\n" +
                "Tipo: " + tipo + "\n" +
                "Marca: " + this.getMarca() + "\n" +
                "Descrição: " + this.getDescricao() + "\n" +
                "Nº Donos: " + this.getNumDonos() + "\n" +
                "Estado: " + estado + "\n" +
                "Preço Base: " + Utils.arrondarCentesimas(this.getPrecoBase()) + "\n" +
                "Preço Final: " + Utils.arrondarCentesimas(this.calcularPreco()) + "\n" +
                "Tamanho: " + this.getTamanho() + "\n" +
                "Padrão: " + InfoUtils.parsePadrao(this.getPadrao()) + "\n" +
                "Transportadora: " + this.getTransportadora().getNome();
    }

}
