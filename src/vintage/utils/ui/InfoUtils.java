package vintage.utils.ui;

public class InfoUtils {

    public static String parseTipoArtigo(int tipoArtigo) {
        switch (tipoArtigo) {
            case 0:
                return "Mala";
            case 1:
                return "Sapatilhas";
            case 2:
                return "T-Shirt";
            default:
                return null;
        }
    }

    public static String parseEstadoUtilizacao(float estadoUtilizacao) {
        if (estadoUtilizacao == 1.00f)
            return "Sem uso";
        else if (estadoUtilizacao == 0.75f)
            return "Pouco uso";
        else if (estadoUtilizacao == 0.50f)
            return "Muito uso";
        else if (estadoUtilizacao == 0.25f)
            return "Estragado";
        return null;
    }

    public static String parseDimensao(int dimensaoEncomenda) {
        switch (dimensaoEncomenda) {
            case 0:
                return "Grande";
            case 1:
                return "Medio";
            case 2:
                return "Pequeno";
            default:
                return null;
        }
    }

    public static String parseEstadoEncomenda(int estadoEncomenda) {
        switch (estadoEncomenda) {
            case 0:
                return "Pendente";
            case 1:
                return "Expedida";
            case 2:
                return "Finalizada";
            default:
                return null;
        }
    }

}
