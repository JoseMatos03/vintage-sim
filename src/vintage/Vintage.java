package vintage;

import java.util.ArrayList;
import java.util.List;

import vintage.artigos.Artigo;
import vintage.artigos.mala.Mala;
import vintage.artigos.sapatilhas.Sapatilhas;
import vintage.artigos.tshirt.TShirt;
import vintage.encomendas.Encomenda;
import vintage.transportadoras.Transportadora;
import vintage.utilizadores.Utilizador;

import static vintage.utils.Utils.getUtilizador;
import static vintage.utils.Utils.getTransportadora;
import static vintage.utils.Utils.getArtigo;

public class Vintage {
    private List<Artigo> artigos;
    private List<Encomenda> encomendas;
    private List<Utilizador> utilizadores;
    private List<Transportadora> transportadoras;
    private int codigoProximoArtigo;

    public void criaArtigo(String[] info) {
        int tipo = Integer.parseInt(info[0]);
        Utilizador vendedor = getUtilizador(utilizadores, Integer.parseInt(info[1]));
        float estadoUtilizacao = Float.parseFloat(info[2]);
        int numDonos = Integer.parseInt(info[3]);
        String descricao = info[4];
        String marca = info[5];
        int codigo = this.codigoProximoArtigo++;
        float precoBase = Float.parseFloat(info[6]);
        Transportadora transportadora = getTransportadora(transportadoras, Integer.parseInt(info[7]));

        switch (tipo) {
            case Artigo.MALA:
                float comprimento = Float.parseFloat(info[8]);
                float largura = Float.parseFloat(info[9]);
                float altura = Float.parseFloat(info[10]);
                float[] dimensao = { comprimento, largura, altura };
                int material = Integer.parseInt(info[11]);
                int anoColecaoMala = Integer.parseInt(info[12]);
                Artigo mala = new Mala(
                        estadoUtilizacao,
                        numDonos,
                        descricao,
                        marca,
                        codigo,
                        precoBase,
                        dimensao,
                        material,
                        anoColecaoMala,
                        vendedor,
                        transportadora);

                this.artigos.add(mala);
                vendedor.criarListagem(mala);
                break;

            case Artigo.SAPATILHAS:
                int tamanhoSapatilhas = Integer.parseInt(info[8]);
                int atacadores = Integer.parseInt(info[9]);
                String cor = info[10];
                int anoColecaoSapatilhas = Integer.parseInt(info[11]);
                Artigo sapatilhas = new Sapatilhas(
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
                        vendedor,
                        transportadora);

                this.artigos.add(sapatilhas);
                vendedor.criarListagem(sapatilhas);
                break;

            case Artigo.TSHIRT:
                String tamanhoTShirt = info[8];
                int padrao = Integer.parseInt(info[9]);
                Artigo tshirt = new TShirt(
                        estadoUtilizacao,
                        numDonos,
                        descricao,
                        marca,
                        codigo,
                        precoBase,
                        tamanhoTShirt,
                        padrao,
                        vendedor,
                        transportadora);

                this.artigos.add(tshirt);
                vendedor.criarListagem(tshirt);
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
        Utilizador vendedor = artigo.getVendedor();

        comprador.comprarArtigo(artigo, vendedor);
        this.artigos.remove(artigo);
    }

    public void removeArtigo(String info) {
        int codigo = Integer.parseInt(info);
        Artigo artigo = getArtigo(artigos, codigo);

        artigo.getVendedor().removerListagem(artigo);
        this.artigos.remove(artigo);
    }

    public void removeArtigo(Artigo artigo) {
        artigo.getVendedor().removerListagem(artigo);
        this.artigos.remove(artigo);
    }

    // TODO Criar encomendas

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

    // TODO Criar transportadora

    // TODO Apagar transportadora

    public Vintage() {
        this.artigos = new ArrayList<>();
        this.encomendas = new ArrayList<>();
        this.utilizadores = new ArrayList<>();
        this.transportadoras = new ArrayList<>();
        this.codigoProximoArtigo = 0;
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
