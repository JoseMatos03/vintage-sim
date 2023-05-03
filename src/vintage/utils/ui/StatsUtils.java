package vintage.utils.ui;

import java.util.List;

import vintage.encomendas.Encomenda;
import vintage.transportadoras.Transportadora;
import vintage.utilizadores.Utilizador;

public class StatsUtils {

    public static int numUtilizadoresAtivos(List<Utilizador> utilizadores) {
        if (utilizadores.size() == 0)
            return 0;

        int utilizadoresAtivos = 0;
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getAtividade() == Utilizador.ATIVA)
                ++utilizadoresAtivos;
        }
        return utilizadoresAtivos;
    }

    public static int numUtilizadoresInativos(List<Utilizador> utilizadores) {
        if (utilizadores.size() == 0)
            return 0;

        int utilizadoresInativos = 0;
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getAtividade() == Utilizador.INATIVA)
                ++utilizadoresInativos;
        }
        return utilizadoresInativos;
    }

    public static int utilizadorComMaiorFaturacao(List<Utilizador> utilizadores) {
        if (utilizadores.size() == 0)
            return -1;

        Utilizador utilizadorComMaiorFaturacao = utilizadores.get(0);
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getValorEmVendas() > utilizadorComMaiorFaturacao.getValorEmVendas())
                utilizadorComMaiorFaturacao = utilizador;
        }
        return utilizadorComMaiorFaturacao.getCodigo();
    }

    public static int numEncomendasPendentes(List<Encomenda> encomendas) {
        if (encomendas.size() == 0)
            return 0;

        int encomendasPendentes = 0;
        for (Encomenda encomenda : encomendas) {
            if (encomenda.getEstadoEncomenda() == Encomenda.PENDENTE)
                ++encomendasPendentes;
        }
        return encomendasPendentes;
    }

    public static int numEncomendasExpedidas(List<Encomenda> encomendas) {
        if (encomendas.size() == 0)
            return 0;

        int encomendasExpedidas = 0;
        for (Encomenda encomenda : encomendas) {
            if (encomenda.getEstadoEncomenda() == Encomenda.EXPEDIDA)
                ++encomendasExpedidas;
        }
        return encomendasExpedidas;
    }

    public static int numEncomendasFinalizadas(List<Encomenda> encomendas) {
        if (encomendas.size() == 0)
            return 0;

        int encomendasFinalizadas = 0;
        for (Encomenda encomenda : encomendas) {
            if (encomenda.getEstadoEncomenda() == Encomenda.FINALIZADA)
                ++encomendasFinalizadas;
        }
        return encomendasFinalizadas;
    }

    public static String transportadoraMaiorValorExpedicao(List<Transportadora> transportadoras) {
        if (transportadoras.size() == 0)
            return "NAN";

        Transportadora transportadoraMaior = transportadoras.get(0);
        for (Transportadora transportadora : transportadoras) {
            if (transportadora.getValorExpedicao() > transportadoraMaior.getValorExpedicao())
                transportadoraMaior = transportadora;
        }
        return transportadoraMaior.getNome();
    }

    public static String transportadoraMaiorLucro(List<Transportadora> transportadoras) {
        if (transportadoras.size() == 0)
            return "NAN";

        Transportadora transportadoraMaior = transportadoras.get(0);
        for (Transportadora transportadora : transportadoras) {
            if (transportadora.getLucro() > transportadoraMaior.getLucro())
                transportadoraMaior = transportadora;
        }
        return transportadoraMaior.getNome();
    }

}
