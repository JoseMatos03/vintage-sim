package vintage.artigos;

import vintage.transportadoras.Transportadora;

public abstract class Artigo {

    private float estadoUtilizacao;
    private int numDonos;
    private String descricao;
    private String marca;
    private int codigo; // TODO Atribuir codigo de forma automática
    private float precoBase;
    private Transportadora transportadora;

    public abstract float calcularPreco();

    public abstract float calcularCorrecao();

    public Artigo(float estadoUtilizacao, int numDonos, String descricao, String marca, float precoBase,
            Transportadora transportadora) {
        this.estadoUtilizacao = estadoUtilizacao;
        this.numDonos = numDonos;
        this.descricao = descricao;
        this.marca = marca;
        this.precoBase = precoBase;
        this.transportadora = transportadora;
    }

    public float getEstadoUtilizacao() {
        return estadoUtilizacao;
    }

    public void setEstadoUtilizacao(float estadoUtilizacao) {
        this.estadoUtilizacao = estadoUtilizacao;
    }

    public int getNumDonos() {
        return numDonos;
    }

    public void setNumDonos(int numDonos) {
        this.numDonos = numDonos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(float precoBase) {
        this.precoBase = precoBase;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

}
