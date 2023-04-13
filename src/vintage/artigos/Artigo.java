package vintage.artigos;

public abstract class Artigo {
    
    private float estadoUtilizacao;
    private int numDonos;
    private String descricao;
    private String marca;
    private String codigo;
    private float precoBase;
    // TODO atribuir uma transportadora

    public abstract float calcularPreco();

    public abstract float calcularCorrecao();

    public Artigo(float estadoUtilizacao, int numDonos, String descricao, String marca, String codigo, float precoBase) {
        this.estadoUtilizacao = estadoUtilizacao;
        this.numDonos = numDonos;
        this.descricao = descricao;
        this.marca = marca;
        this.codigo = codigo;
        this.precoBase = precoBase;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(float precoBase) {
        this.precoBase = precoBase;
    }
}
