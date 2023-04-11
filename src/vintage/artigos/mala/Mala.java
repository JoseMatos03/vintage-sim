package vintage.artigos.mala;

import vintage.artigos.Artigo;

public class Mala extends Artigo {
    // Tipos de material
    public static final int TECIDO = 0;
    public static final int PELE = 1;
    public static final int LONA = 2;
    public static final int VELUDO = 3;

    private int[] dimensao = new int[2]; // [w, h]
    private int material;
    private int anoColecao;

    @Override
    public int calcularPreco() {
        // TODO calcular os descontos
        // RESTRIÇÕES:
        // Desproporcionalmente inverso às dimensões
        return this.getPrecoBase();
    }

    public Mala(float estadoUtilizacao, int numDonos, String descricao, String marca, String codigo, int precoBase,
            int[] dimensao, int material, int anoColecao) {
        super(estadoUtilizacao, numDonos, descricao, marca, codigo, precoBase);

        this.dimensao = dimensao;
        this.material = material;
        this.anoColecao = anoColecao;
    }

    public int[] getDimensao() {
        return dimensao;
    }

    public void setDimensao(int[] dimensao) {
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
}
