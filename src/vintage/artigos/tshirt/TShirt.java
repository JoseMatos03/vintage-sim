package vintage.artigos.tshirt;

import vintage.artigos.Artigo;

public class TShirt extends Artigo {
    // Tipos de padrão
    public static final int LISO = 0;
    public static final int RISCOS = 1;
    public static final int PALMEIRAS = 2;

    private int tamanho;
    private int padrao;

    @Override
    public int calcularPreco() {
        // TODO calcular os descontos
        // RESTRIÇÕES:
        // PADRÃO LISO -> Sem descontos
        // USADOS -> Desconto fixo 50%;
        return this.getPrecoBase();
    }

    public TShirt(float estadoUtilizacao, int numDonos, String descricao, String marca, String codigo, int precoBase,
            int tamanho, int padrao) {
        super(estadoUtilizacao, numDonos, descricao, marca, codigo, precoBase);

        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getPadrao() {
        return padrao;
    }

    public void setPadrao(int padrao) {
        this.padrao = padrao;
    }
}
