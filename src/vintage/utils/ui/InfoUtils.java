package vintage.utils.ui;

import java.util.List;

import vintage.artigos.Artigo;

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

    public static String parseTipoAtacadores(int tipoAtacadores) {
        switch (tipoAtacadores) {
            case 0:
                return "Atacadores";
            case 1:
                return "Atilhos";
            default:
                return null;
        }
    }

    public static String parsePadrao(int padrao) {
        switch (padrao) {
            case 0:
                return "Liso";
            case 1:
                return "Riscos";
            case 2:
                return "Palmeiras";
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
            case 10:
                return "Grande";
            case 5:
                return "Medio";
            case 1:
                return "Pequeno";
            default:
                return null;
        }
    }

    public static String parseEstadoAtividade(int estadoAtividade) {
        switch (estadoAtividade) {
            case 0:
                return "Ativa";
            case 1:
                return "Inativa";
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

    public static String parseListaArtigos(List<Artigo> artigos) {
        String string = "[";
        for (Artigo artigo : artigos) {
            int codigo = artigo.getCodigo();
            string += codigo + ",";
        }
        if (string.endsWith(",")) {
            return string.substring(0, string.length() - 1) + "]";
        }
        return string + "]";
    }
}
