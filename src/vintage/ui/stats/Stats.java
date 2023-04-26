package vintage.ui.stats;

import java.util.List;

import vintage.Vintage;
import vintage.utilizadores.Utilizador;
import vintage.transportadoras.Transportadora;
import vintage.encomendas.Encomenda;

public class Stats {

    public static int vendedorMaiorFaturacao(Vintage vintage) // TODO Implementar intervalos de tempo
    {
        int codigoUtilizador = 0;
        float valorVendas = 0;
        List<Utilizador> utilizadores = vintage.getUtilizadores();
        for(Utilizador utilizador : utilizadores)
        {
            float valorVendasAtual = utilizador.getValorEmVendas();
            if (valorVendasAtual > valorVendas) {
                valorVendas = valorVendasAtual;
                codigoUtilizador = utilizador.getCodigo();
            }
        }
        return codigoUtilizador;
    }
    
    public static String transportadoraMaiorFaturacao(Vintage vintage)
    {
        String transportadoraMaior = "";
        float maiorFaturacao = 0;
        List<Transportadora> transportadoras = vintage.getTransportadoras();
        for(Transportadora transportadora : transportadoras)
        {
            float valorExpedicao = transportadora.getValorExpedicao();
            if (valorExpedicao > maiorFaturacao) {
                maiorFaturacao = valorExpedicao;
                transportadoraMaior = transportadora.getNome();
            }
        }
        return transportadoraMaior;
    }

    // TODO Listar encomendas emitidas por um vendedor

    public static float totalGanho(Vintage vintage)
    {
        List<Encomenda> encomendas = vintage.getEncomendas();
        float ganhos = 0;
        for (Encomenda encomenda : encomendas) {
            ganhos += encomenda.getPrecoEncomenda();
        }
        return ganhos;
    }
}
