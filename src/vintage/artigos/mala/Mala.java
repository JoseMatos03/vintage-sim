package vintage.artigos.mala;

import vintage.artigos.Artigo;
import vintage.transportadoras.Transportadora;
import vintage.utils.artigos.Utils;
import vintage.utils.ui.InfoUtils;

public class Mala extends Artigo {

    // Tipos de material
    public static final int TECIDO = 0;
    public static final int PELE = 1;
    public static final int LONA = 2;
    public static final int VELUDO = 3;

    // Dimensões
    public static final int COMPRIMENTO = 0;
    public static final int LARGURA = 1;
    public static final int ALTURA = 2;

    // Controla a correção dada relativa à dimensão
    public static final int CONSTANTE_CORRECAO = 50;
    public static final int MARGEM_ERRO = 25;

    private float[] dimensao; // [c, l, a]
    private int material;
    private int anoColecao;

    public Mala(int tipo, float estadoUtilizacao, int numDonos, String descricao, String marca, int codigo,
            float precoBase,
            float[] dimensao, int material, int anoColecao, int codigoVendedor, Transportadora transportadora,
            boolean premiumEstado) {
        super(tipo, estadoUtilizacao, numDonos, descricao, marca, codigo, precoBase, codigoVendedor, transportadora,
                premiumEstado);

        this.dimensao = dimensao;
        this.material = material;
        this.anoColecao = anoColecao;
    }

    @Override
    public float calcularPreco() {
        float precoFinal = this.getPrecoBase() + this.calcularCorrecaoPremium(this.getAnoColecao())
                + this.calcularCorrecao();
        return precoFinal;
    }

    @Override
    public float calcularCorrecao() {
        float comprimento = this.getDimensao()[COMPRIMENTO];
        float largura = this.getDimensao()[LARGURA];
        float altura = this.getDimensao()[ALTURA];
        float dimensao = calcularDimensao(comprimento, largura, altura);

        float correcao = 0;

        correcao += Utils.calcularPercentagem(this.getPrecoBase(), this.getTransportadora().getValorExpedicao());
        correcao -= (1f / dimensao) * this.getPrecoBase() * CONSTANTE_CORRECAO;

        return correcao;
    }

    public static float calcularDimensao(float c, float l, float a) {
        return c * l * a;
    }

    public float[] getDimensao() {
        return dimensao;
    }

    public void setDimensao(float[] dimensao) {
        this.dimensao = dimensao;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
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
                "Nº Donos: " + this.getNumDonos() + "\n" +
                "Estado: " + estado + "\n" +
                "Preço Base: " + Utils.arrondarCentesimas(this.getPrecoBase()) + "\n" +
                "Preço Final: " + Utils.arrondarCentesimas(this.calcularPreco()) + "\n" +
                "Dimensão: " + InfoUtils.parseDimensoes(dimensao) + "\n" +
                "Material: " + material + "\n" +
                "Ano Coleção: " + anoColecao + "\n" +
                "Transportadora: " + this.getTransportadora().getNome() + "\n" +
                "Premium: " + premium;
    }

}
