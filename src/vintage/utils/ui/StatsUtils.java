package vintage.utils.ui;

import java.util.List;

import vintage.utilizadores.Utilizador;

public class StatsUtils {

    public static int numUtilizadoresAtivos(List<Utilizador> utilizadores) {
        int utilizadoresAtivos = 0;
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getAtividade() == Utilizador.ATIVA)
                ++utilizadoresAtivos;
        }
        return utilizadoresAtivos;
    }

    public static int numUtilizadoresInativos(List<Utilizador> utilizadores) {
        int utilizadoresInativos = 0;
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getAtividade() == Utilizador.INATIVA)
                ++utilizadoresInativos;
        }
        return utilizadoresInativos;
    }

    public static int utilizadorComMaiorFaturacao(List<Utilizador> utilizadores) {
        Utilizador utilizadorComMaiorFaturacao = utilizadores.get(0);
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getValorEmVendas() > utilizadorComMaiorFaturacao.getValorEmVendas())
                utilizadorComMaiorFaturacao = utilizador;
        }
        return utilizadorComMaiorFaturacao.getCodigo();
    }

}
