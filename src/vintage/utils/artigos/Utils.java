package vintage.utils.artigos;

public class Utils {

    public static float arrondarDecimas(float num) {
        return Math.round(num * 10) / 10f;
    }

    public static float arrondarCentesimas(float num) {
        return Math.round(num * 100) / 100f;
    }

    public static float calcularPercentagem(float num, float percentagem) {
        return (num*(percentagem/100f));
    }

}
