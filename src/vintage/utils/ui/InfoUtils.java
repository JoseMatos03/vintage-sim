package vintage.utils.ui;

public class InfoUtils {
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
