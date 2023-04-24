package vintage;

import static vintage.utils.vintage.Utils.getArtigo;
import static vintage.utils.vintage.Utils.getEncomenda;
import static vintage.utils.vintage.Utils.getTransportadora;
import static vintage.utils.vintage.Utils.getUtilizador;

import java.util.ArrayList;
import java.util.List;

import vintage.artigos.Artigo;
import vintage.artigos.mala.Mala;
import vintage.artigos.sapatilhas.Sapatilhas;
import vintage.artigos.tshirt.TShirt;
import vintage.encomendas.Encomenda;
import vintage.transportadoras.Transportadora;
import vintage.utilizadores.Utilizador;

public class Vintage {
    private List<Artigo> artigos;
    private List<Encomenda> encomendas;
    private List<Utilizador> utilizadores;
    private List<Transportadora> transportadoras;
    private int codigoProximoArtigo;

    public void criaArtigo(String[] info) {
        int tipo = Integer.parseInt(info[0]);
        int codigoVendedor = Integer.parseInt(info[1]);
        float estadoUtilizacao = Float.parseFloat(info[2]);
        int numDonos = Integer.parseInt(info[3]);
        String descricao = info[4];
        String marca = info[5];
        int codigo = this.codigoProximoArtigo++;
        float precoBase = Float.parseFloat(info[6]);
        Transportadora transportadora = getTransportadora(transportadoras, info[7]);

        switch (tipo) {
            case Artigo.MALA:
                float comprimento = Float.parseFloat(info[8]);
                float largura = Float.parseFloat(info[9]);
                float altura = Float.parseFloat(info[10]);
                float[] dimensao = { comprimento, largura, altura };
                int material = Integer.parseInt(info[11]);
                int anoColecaoMala = Integer.parseInt(info[12]);
                Artigo mala = new Mala(
                        tipo,
                        estadoUtilizacao,
                        numDonos,
                        descricao,
                        marca,
                        codigo,
                        precoBase,
                        dimensao,
                        material,
                        anoColecaoMala,
                        codigoVendedor,
                        transportadora);

                this.artigos.add(mala);
                getUtilizador(utilizadores, codigoVendedor).criarListagem(mala);
                break;

            case Artigo.SAPATILHAS:
                int tamanhoSapatilhas = Integer.parseInt(info[8]);
                int atacadores = Integer.parseInt(info[9]);
                String cor = info[10];
                int anoColecaoSapatilhas = Integer.parseInt(info[11]);
                Artigo sapatilhas = new Sapatilhas(
                        tipo,
                        estadoUtilizacao,
                        numDonos,
                        descricao,
                        marca,
                        codigo,
                        precoBase,
                        tamanhoSapatilhas,
                        atacadores,
                        cor,
                        anoColecaoSapatilhas,
                        codigoVendedor,
                        transportadora);

                this.artigos.add(sapatilhas);
                getUtilizador(utilizadores, codigoVendedor).criarListagem(sapatilhas);
                break;

            case Artigo.TSHIRT:
                String tamanhoTShirt = info[8];
                int padrao = Integer.parseInt(info[9]);
                Artigo tshirt = new TShirt(
                        tipo,
                        estadoUtilizacao,
                        numDonos,
                        descricao,
                        marca,
                        codigo,
                        precoBase,
                        tamanhoTShirt,
                        padrao,
                        codigoVendedor,
                        transportadora);

                this.artigos.add(tshirt);
                getUtilizador(utilizadores, codigoVendedor).criarListagem(tshirt);
                break;

            default:
                break;
        }
    }

    public void compraArtigo(String[] info) {
        int codigoArtigo = Integer.parseInt(info[0]);
        int codigoComprador = Integer.parseInt(info[1]);
        Artigo artigo = getArtigo(artigos, codigoArtigo);
        Utilizador comprador = getUtilizador(utilizadores, codigoComprador);
        Utilizador vendedor = getUtilizador(utilizadores, artigo.getCodigoVendedor());

        comprador.comprarArtigo(artigo, vendedor);
        this.artigos.remove(artigo);
    }

    public void removeArtigo(String info) {
        int codigo = Integer.parseInt(info);
        Artigo artigo = getArtigo(artigos, codigo);

        getUtilizador(utilizadores, artigo.getCodigoVendedor()).removerListagem(artigo);
        this.artigos.remove(artigo);
    }

    public void removeArtigo(Artigo artigo) {
        getUtilizador(utilizadores, artigo.getCodigoVendedor()).removerListagem(artigo);
        this.artigos.remove(artigo);
    }

    public void criaEncomenda(String[] info) {
        int codigo = encomendas.size();
        int dimensaoEncomenda = Integer.parseInt(info[0]);

        Encomenda encomenda = new Encomenda(codigo, dimensaoEncomenda);
        this.encomendas.add(encomenda);
    }

    public void cancelaEncomenda(String info) {
        int codigo = Integer.parseInt(info);
        Encomenda encomenda = getEncomenda(encomendas, codigo);
        encomenda.reembolsar();
    }

    public void criaUtilizador(String[] info) {
        int codigo = utilizadores.size();
        String email = info[0];
        String nome = info[1];
        String morada = info[2];
        int numeroFiscal = Integer.parseInt(info[3]);

        Utilizador utilizador = new Utilizador(
                codigo,
                email,
                nome,
                morada,
                numeroFiscal);
        this.utilizadores.add(utilizador);
    }

    public void apagaUtilizador(String info) {
        int codigo = Integer.parseInt(info);
        Utilizador utilizador = getUtilizador(utilizadores, codigo);

        utilizador.setEmail(null);
        utilizador.setNome(null);
        utilizador.setMorada(null);
        utilizador.setNumeroFiscal(0);
        for (Artigo artigo : utilizador.getListados())
            this.removeArtigo(artigo);
        utilizador.setListados(null);
        utilizador.setAtividade(Utilizador.INATIVA);
    }

    public void criaTransportadora(String[] info) {
        String nome = info[0];
        float margemLucro = Float.parseFloat(info[1]);
        float margemExtra = Float.parseFloat(info[2]);

        Transportadora transportadora = new Transportadora(
                nome,
                margemLucro,
                margemExtra);

        this.transportadoras.add(transportadora);
    }

    public void apagaTransportadora(String nome) {
        Transportadora transportadora = getTransportadora(transportadoras, nome);
        this.transportadoras.remove(transportadora);
    }

    public Vintage() {
        this.artigos = new ArrayList<>();
        this.encomendas = new ArrayList<>();
        this.utilizadores = new ArrayList<>();
        this.transportadoras = new ArrayList<>();
        this.codigoProximoArtigo = 0;
    }

    public Vintage(Vintage loja) {
        this.artigos = loja.getArtigos();
        this.encomendas = loja.getEncomendas();
        this.utilizadores = loja.getUtilizadores();
        this.transportadoras = loja.getTransportadoras();
        this.codigoProximoArtigo = loja.getCodigoProximoArtigo();
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

    public int getCodigoProximoArtigo() {
        return codigoProximoArtigo;
    }

    public void setCodigoProximoArtigo(int codigoProximoArtigo) {
        this.codigoProximoArtigo = codigoProximoArtigo;
    }

}
