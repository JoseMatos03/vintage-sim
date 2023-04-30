package vintage.utils.ui;

public class ManageUtils {

    public static String parsePremiumBoolean(String premium)
    {
        switch(premium){
            case "Sim":
                return "true";
            case "Não":
                return "false";
            default:
                return null;
        }
    }
    public static String parseEstadoUtilizacao(String estadoUtilizacao) {
        switch (estadoUtilizacao) {
            case "Sem uso":
                return "1.00";
            case "Pouco uso":
                return "0.75";
            case "Muito uso":
                return "0.50";
            case "Estragado":
                return "0.25";
            default:
                return null;
        }
    }

    public static String parseMaterialMala(String material) {
        switch (material) {
            case "Tecido":
                return "0";
            case "Pele":
                return "1";
            case "Lona":
                return "2";
            case "Veludo":
                return "3";
            default:
                return null;
        }
    }

    public static String parseTamanhoEncomenda(String tamanho) {
        switch (tamanho) {
            case "Grande":
                return "10";
            case "Médio":
                return "5";
            case "Pequeno":
                return "1";
            default:
                return null;
        }
    }

}
