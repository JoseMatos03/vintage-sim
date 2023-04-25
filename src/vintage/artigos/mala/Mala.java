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

    private final float CONSTANTECORRECAO = this.getPrecoBase() * 50; // Controla a correção dada relativa à dimensão

    private float[] dimensao; // [c, l, a]
    private int material;
    private int anoColecao;

    @Override
    public float calcularPreco() {
        float precoFinal = this.getPrecoBase() + this.calcularCorrecao();
        return precoFinal;
    }

    @Override
    public float calcularCorrecao() {
        float dimensao = this.calcularDimensao();
        float correcao = 0;

        correcao += this.getTransportadora().getValorExpedicao();
        correcao -= (1f / dimensao) * CONSTANTECORRECAO;

        return Utils.arrondarCentesimas(correcao);
    }

    public float calcularDimensao() {
        float comprimento = this.getDimensao()[COMPRIMENTO];
        float largura = this.getDimensao()[LARGURA];
        float altura = this.getDimensao()[ALTURA];

        float dimensao = comprimento * largura * altura;
        return Utils.arrondarDecimas(dimensao);
    }

    public Mala(int tipo, float estadoUtilizacao, int numDonos, String descricao, String marca, int codigo,
            float precoBase,
            float[] dimensao, int material, int anoColecao, int codigoVendedor, Transportadora transportadora) {
        super(tipo, estadoUtilizacao, numDonos, descricao, marca, codigo, precoBase, codigoVendedor, transportadora);

        this.dimensao = dimensao;
        this.material = material;
        this.anoColecao = anoColecao;
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

        return "Código: " + this.getCodigo() + "\n" +
                "Código Vendedor: " + this.getCodigoVendedor() + "\n" +
                "Tipo: " + tipo + "\n" +
                "Marca: " + this.getMarca() + "\n" +
                "Descrição: " + this.getDescricao() + "\n" +
                "Ano coleção: " + anoColecao + "\n" +
                "Nº Donos: " + this.getNumDonos() + "\n" +
                "Estado: " + estado + "\n" +
                "Preço Base: " + this.getPrecoBase() + "\n" +
                "Preço Final: " + this.calcularPreco() + "\n" +
                "Transportadora: " + this.getTransportadora().getNome() + "\n";
    }

}
