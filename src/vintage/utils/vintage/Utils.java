package vintage.utils.vintage;

import java.util.List;

import vintage.artigos.Artigo;
import vintage.encomendas.Encomenda;
import vintage.transportadoras.Transportadora;
import vintage.utilizadores.Utilizador;

public class Utils {

    public static Artigo getArtigo(List<Artigo> artigos, int codigo) {
        for (Artigo artigo : artigos) {
            if (artigo.getCodigo() == codigo) {
                return artigo;
            }
        }
        return null;
    }

    public static Encomenda getEncomenda(List<Encomenda> encomendas, int codigo) {
        for (Encomenda encomenda : encomendas) {
            if (encomenda.getCodigo() == codigo) {
                return encomenda;
            }
        }
        return null;
    }

    public static Utilizador getUtilizador(List<Utilizador> utilizadores, int codigo) {
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getCodigo() == codigo) {
                return utilizador;
            }
        }
        return null;
    }

    public static Transportadora getTransportadora(List<Transportadora> transportadoras, String nome) {
        for (Transportadora transportadora : transportadoras) {
            if (transportadora.getNome().equals(nome)) {
                return transportadora;
            }
        }
        return null;
    }

    public static int getEncomendaOfArtigo(List<Encomenda> encomendas, Artigo artigo) {
        for (Encomenda encomenda : encomendas) {
            if (encomenda.getArtigos().contains(artigo.getCodigo())) {
                return encomenda.getCodigo();
            }
        }
        return -1;
    }

    public static boolean isArtigoInEncomendaExpedida(List<Encomenda> encomendas, Artigo artigo) {
        for (Encomenda encomenda : encomendas) {
            if (encomenda.getEstadoEncomenda() == Encomenda.PENDENTE)
                continue;
                
            if (encomenda.getArtigos().contains(artigo.getCodigo())) {
                return true;
            }
        }
        return false;
    }

}
