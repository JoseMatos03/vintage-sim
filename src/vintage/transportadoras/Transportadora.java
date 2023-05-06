package vintage.transportadoras;

import static vintage.utils.transportadoras.Utils.IMPOSTO;
import static vintage.utils.transportadoras.Utils.VALORBASE;

import vintage.utils.ErrorCode;
import vintage.utils.artigos.Utils;
import vintage.utils.ui.InfoUtils;

public class Transportadora {

    private String nome;
    private float margemLucro;
    private float margemExtra;
    private float valorExpedicao;
    private float lucro;
    private boolean premiumEstado;

    public Transportadora(String nome, float margemLucro, float margemExtra, boolean premiumEstado) {
        this.nome = nome;
        this.margemLucro = margemLucro;
        this.margemExtra = margemExtra;
        this.valorExpedicao = calcularValorExpedicao();
        this.lucro = 0.00f;
        this.premiumEstado = premiumEstado;
    }

    public ErrorCode atualizarValores(String... info) {
        try {
            float margemLucro = Float.parseFloat(info[0]);
            float margemExtra = Float.parseFloat(info[1]);

            setMargemLucro(margemLucro);
            setMargemExtra(margemExtra);
            setValorExpedicao(calcularValorExpedicao());
        } catch (Exception e) {
            return ErrorCode.PARAMETRO_ERRADO;
        }
        return ErrorCode.NO_ERRORS;
    }

    public void calcularEntrega(float precoArtigo) {
        lucro += Utils.calcularPercentagem(precoArtigo, valorExpedicao);
    }

    public float calcularValorExpedicao() {
        return this.premiumEstado ? (VALORBASE * margemLucro * (1 + IMPOSTO)) * margemExtra
                : VALORBASE * margemLucro * (1 + IMPOSTO);
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

    public boolean getPremiumEstado() {
        return premiumEstado;
    }

    public void setPremiumEstado(boolean premiumEstado) {
        this.premiumEstado = premiumEstado;
    }

    @Override
    public String toString() {
        String premium = InfoUtils.parsePremium(this.getPremiumEstado());

        return "Nome: " + nome + "\n" +
                "Margem Lucro: " + Utils.arrondarCentesimas(margemLucro) + "\n" +
                "Margem Extra: " + Utils.arrondarCentesimas(margemExtra) + "\n" +
                "Valor Expedição: " + Utils.arrondarCentesimas(valorExpedicao) + "\n" +
                "Lucro: " + Utils.arrondarCentesimas(lucro) + "\n" +
                "Premium: " + premium;
    }

}
