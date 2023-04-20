package vintage;

import java.util.ArrayList;
import java.util.List;

import vintage.artigos.Artigo;
import vintage.encomendas.Encomenda;
import vintage.transportadoras.Transportadora;
import vintage.utilizadores.Utilizador;

public class Vintage {
    private List<Artigo> artigos;
    private List<Encomenda> encomendas;
    private List<Utilizador> utilizadores;
    private List<Transportadora> transportadoras;

    // Opções são dadas como argumentos da consola
    public void criaUtilizador(String[] info) {
        int codigo = utilizadores.size();
        String email = info[0];
        String nome = info[1];
        String morada = info[2];
        int numeroFiscal = Integer.parseInt(info[3]);
        
        Utilizador utilizador = new Utilizador();
        utilizador.setCodigo(codigo);
        utilizador.setEmail(email);
        utilizador.setNome(nome);
        utilizador.setMorada(morada);
        utilizador.setNumeroFiscal(numeroFiscal);

        this.utilizadores.add(utilizador);
    }

    public Vintage() {
        this.artigos = new ArrayList<>();
        this.encomendas = new ArrayList<>();
        this.utilizadores = new ArrayList<>();
        this.transportadoras = new ArrayList<>();
    }

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }

    public List<Encomenda> getEncomendas() {
        return encomendas;
    }

    public void setEncomendas(List<Encomenda> encomendas) {
        this.encomendas = encomendas;
    }

    public List<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(List<Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }

    public List<Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public void setTransportadoras(List<Transportadora> transportadoras) {
        this.transportadoras = transportadoras;
    }

    

    
    
}
