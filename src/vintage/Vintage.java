package vintage;

import java.util.ArrayList;
import java.util.List;

import vintage.artigos.Artigo;
import vintage.artigos.mala.Mala;
import vintage.encomendas.Encomenda;
import vintage.transportadoras.Transportadora;
import vintage.utilizadores.Utilizador;

public class Vintage {
    private List<Artigo> artigos;
    private List<Encomenda> encomendas;
    private List<Utilizador> utilizadores;
    private List<Transportadora> transportadoras;

    public void criaArtigo(String[] info) {
        Utilizador utilizador = null; // TODO encontrar utilizador
        int tipo = Integer.parseInt(info[1]);
        int estadoUtilizacao = Integer.parseInt(info[2]);
        int numDonos = Integer.parseInt(info[3]);
        String descricao = info[4];
        String marca = info[5];
        int codigo = 0; // TODO Atribuir codigo de forma automática
        float precoBase = Integer.parseInt(info[6]);
        Transportadora transportadora = null; // TODO encontrar transportadora

        switch (tipo) {
            case Artigo.MALA:
                float comprimento = Integer.parseInt(info[7]);
                float largura = Integer.parseInt(info[8]);
                float altura = Integer.parseInt(info[9]);
                float[] dimensao = { comprimento, largura, altura };
                int material = Integer.parseInt(info[10]);
                int anoColecao = Integer.parseInt(info[11]);
                Artigo mala = new Mala(
                        estadoUtilizacao,
                        numDonos,
                        descricao,
                        marca,
                        codigo,
                        precoBase,
                        dimensao,
                        material,
                        anoColecao,
                        transportadora);

                this.artigos.add(mala);
                utilizador.criarListagem(mala);
                break;

            case Artigo.SAPATILHAS:

                break;

            case Artigo.TSHIRT:

                break;

            default:
                break;
        }
    }

    // TODO Remover artigo

    // TODO Criar encomendas

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

    // TODO Apagar utilizador

    // TODO Criar transportadora

    // TODO Apagar transportadora

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
