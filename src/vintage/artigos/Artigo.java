package vintage.artigos;

import java.time.LocalDateTime;

import vintage.transportadoras.Transportadora;
import vintage.utils.artigos.Utils;

public abstract class Artigo {

    // Tipo de artigo
    public static final int MALA = 0;
    public static final int SAPATILHAS = 1;
    public static final int TSHIRT = 2;

    private int tipo;
    private float estadoUtilizacao;
    private int numDonos;
    private String descricao;
    private String marca;
    private int codigo;
    private float precoBase;
    private int codigoVendedor;
    private Transportadora transportadora;
    private boolean premiumEstado;

    public abstract float calcularPreco();

    public abstract float calcularCorrecao();

    public Artigo(int tipo, float estadoUtilizacao, int numDonos, String descricao, String marca, int codigo,
            float precoBase,
            int codigoVendedor, Transportadora transportadora, boolean premiumEstado) {
        this.tipo = tipo;
        this.estadoUtilizacao = estadoUtilizacao;
        this.numDonos = numDonos;
        this.descricao = descricao;
        this.marca = marca;
        this.codigo = codigo;
        this.precoBase = precoBase;
        this.codigoVendedor = codigoVendedor;
        this.transportadora = transportadora;
        this.premiumEstado = premiumEstado;
    }

    public float calcularCorrecaoPremium(int anoColecao)
    {
        float precoPremium = 0;
        if(this.getPremiumEstado() == true){
            precoPremium += this.getPrecoBase() * ((LocalDateTime.now().getYear() - anoColecao) / Utils.arrondarCentesimas(100));
        }
        return precoPremium;
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

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public void setPremiumEstado(boolean premiumEstado)
    {
        this.premiumEstado = premiumEstado;
    }
    public boolean getPremiumEstado()
    {
        return premiumEstado;
    }
}
