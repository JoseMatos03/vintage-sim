package vintage.artigos.tshirt;

import vintage.artigos.Artigo;
import vintage.transportadoras.Transportadora;

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

    @Override
    public float calcularPreco() {
        float precoFinal = this.getPrecoBase() + this.calcularCorrecao();
        return precoFinal;
    }

    @Override
    public float calcularCorrecao() {
        float correcao = 0;

        correcao += this.getTransportadora().getValorExpedicao();
        if (this.padrao == LISO) {
            return 0;
        }
        if (this.getEstadoUtilizacao() != 1f) {
            correcao -= this.getPrecoBase() * 0.5f;
        }

        return correcao;
    }

    public TShirt(float estadoUtilizacao, int numDonos, String descricao, String marca, int codigo, int precoBase,
            String tamanho, int padrao, Transportadora transportadora) {
        super(estadoUtilizacao, numDonos, descricao, marca, codigo, precoBase, transportadora);

        this.tamanho = tamanho;
        this.padrao = padrao;
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
}
