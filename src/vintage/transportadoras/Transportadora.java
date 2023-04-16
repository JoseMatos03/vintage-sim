package vintage.transportadoras;

import static vintage.utils.transportadoras.Utils.VALORBASE;
import static vintage.utils.transportadoras.Utils.IMPOSTO;

public class Transportadora {

    private float margemLucro;
    private float margemExtra;
    private float valorExpedicao;

    public float calcularValorExpedicao() {
        return (VALORBASE * margemLucro * (1 + IMPOSTO)) * margemExtra;
    }

    public Transportadora(float margemLucro) {
        this.margemLucro = margemLucro;
        this.margemExtra = 1.00f;
        this.valorExpedicao = calcularValorExpedicao();
    }

    public Transportadora(float margemLucro, float margemExtra) {
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
}
