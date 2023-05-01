package vintage.transportadoras;

import static vintage.utils.transportadoras.Utils.VALORBASE;

import vintage.utils.artigos.Utils;

import static vintage.utils.transportadoras.Utils.IMPOSTO;

public class Transportadora {

    private String nome;
    private float margemLucro;
    private float margemExtra;
    private float valorExpedicao;
    private float lucro;

    public void calcularEntrega(float precoArtigo) {
        lucro += Utils.calcularPercentagem(precoArtigo, valorExpedicao);
    }

    public float calcularValorExpedicao() {
        return (VALORBASE * margemLucro * (1 + IMPOSTO)) * margemExtra;
    }

    public Transportadora(String nome, float margemLucro, float margemExtra) {
        this.nome = nome;
        this.margemLucro = margemLucro;
        this.margemExtra = margemExtra;
        this.valorExpedicao = calcularValorExpedicao();
        this.lucro = 0.00f;
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

    public float getLucro() {
        return lucro;
    }

    public void setLucro(float lucro) {
        this.lucro = lucro;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Margem Lucro: " + Utils.arrondarCentesimas(margemLucro) + "\n" +
                "Margem Extra: " + Utils.arrondarCentesimas(margemExtra) + "\n" +
                "Valor Expedição: " + Utils.arrondarCentesimas(valorExpedicao) + "\n" +
                "Lucro: " + Utils.arrondarCentesimas(lucro);
    }

}
