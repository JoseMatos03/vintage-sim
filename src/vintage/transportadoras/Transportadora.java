package vintage.transportadoras;

import static vintage.utils.transportadoras.Utils.VALORBASE;
import static vintage.utils.transportadoras.Utils.IMPOSTO;

public class Transportadora {

    private String nome;
    private float margemLucro;
    private float margemExtra;
    private float valorExpedicao;

    public float calcularValorExpedicao() {
        return (VALORBASE * margemLucro * (1 + IMPOSTO)) * margemExtra;
    }

    public Transportadora(String nome, float margemLucro, float margemExtra) {
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

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Margem Lucro: " + margemLucro + "\n" +
                "Margem Extra: " + margemExtra + "\n" +
                "Valor Expedição: " + valorExpedicao;
    }

}
