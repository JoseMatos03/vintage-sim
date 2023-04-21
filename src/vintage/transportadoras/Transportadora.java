package vintage.transportadoras;

import static vintage.utils.transportadoras.Utils.VALORBASE;
import static vintage.utils.transportadoras.Utils.IMPOSTO;

public class Transportadora {

    private int codigo;
    private String nome;
    private float margemLucro;
    private float margemExtra;
    private float valorExpedicao;

    public float calcularValorExpedicao() {
        return (VALORBASE * margemLucro * (1 + IMPOSTO)) * margemExtra;
    }

    public Transportadora(int codigo, String nome, float margemLucro) {
        this.codigo = codigo;
        this.nome = nome;
        this.margemLucro = margemLucro;
        this.margemExtra = 1.00f;
        this.valorExpedicao = calcularValorExpedicao();
    }

    public Transportadora(int codigo, String nome, float margemLucro, float margemExtra) {
        this.codigo = codigo;
        this.nome = nome;
        this.margemLucro = margemLucro;
        this.margemExtra = margemExtra;
        this.valorExpedicao = calcularValorExpedicao();
    }

    public float getValorExpedicao() {
        return valorExpedicao;
    }

    public void setValorExpedicao(float valorExpedicao) {
        this.valorExpedicao = valorExpedicao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(float margemLucro) {
        this.margemLucro = margemLucro;
    }

    public float getMargemExtra() {
        return margemExtra;
    }

    public void setMargemExtra(float margemExtra) {
        this.margemExtra = margemExtra;
    }
}
