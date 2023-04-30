package vintage;

import static vintage.utils.vintage.Utils.getArtigo;
import static vintage.utils.vintage.Utils.getEncomenda;
import static vintage.utils.vintage.Utils.getTransportadora;
import static vintage.utils.vintage.Utils.getUtilizador;
import static vintage.utils.vintage.Utils.getEncomendaOfArtigo;
import static vintage.utils.vintage.Utils.isArtigoInEncomendaExpedida;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import vintage.artigos.Artigo;
import vintage.artigos.mala.Mala;
import vintage.artigos.sapatilhas.Sapatilhas;
import vintage.artigos.tshirt.TShirt;
import vintage.encomendas.Encomenda;
import vintage.transportadoras.Transportadora;
import vintage.utilizadores.Utilizador;
import vintage.utils.ErrorCode;
import vintage.utils.ui.StatsUtils;

public class Vintage {
    private List<Artigo> artigos;
    private List<Encomenda> encomendas;
    private List<Utilizador> utilizadores;
    private List<Transportadora> transportadoras;

    private int codigoProximoArtigo;
    private int numVendas;
    private float totalFaturado;

    private LocalDateTime tempoAtual;

    public ErrorCode criaArtigo(String[] info) {
        try {
            int tipo = Integer.parseInt(info[0]);
            int codigoVendedor = Integer.parseInt(info[1]);
            float estadoUtilizacao = Float.parseFloat(info[2]);
            int numDonos = Integer.parseInt(info[3]);
            String descricao = info[4];
            String marca = info[5];
            int codigo = this.codigoProximoArtigo++;
            float precoBase = Float.parseFloat(info[6]);
            Transportadora transportadora = getTransportadora(transportadoras, info[7]);

            if (getUtilizador(utilizadores, codigoVendedor) == null)
                return ErrorCode.CODIGO_INVALIDO;

            if (transportadora == null)
                return ErrorCode.TRANSPORTADORA_INVALIDA;

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
        } catch (Exception e) {
            return ErrorCode.PARAMETRO_ERRADO;
        }
        return ErrorCode.NO_ERRORS;
    }

    public ErrorCode removeArtigo(String info) {
        int codigo = Integer.parseInt(info);
        Artigo artigo = getArtigo(artigos, codigo);

        if (isArtigoInEncomendaExpedida(encomendas, artigo))
            return ErrorCode.ARTIGO_EXPEDIDO;

        if (getEncomendaOfArtigo(encomendas, artigo) != -1) {
            Encomenda encomenda = getEncomenda(encomendas, getEncomendaOfArtigo(encomendas, artigo));
            encomenda.removerArtigo(artigos, codigo);
        }
        getUtilizador(utilizadores, artigo.getCodigoVendedor()).removerListagem(artigo);
        this.artigos.remove(artigo);

        return ErrorCode.NO_ERRORS;
    }

    public ErrorCode criaEncomenda(String[] info) {
        try {
            int codigo = encomendas.size();
            int codigoComprador = Integer.parseInt(info[0]);
            int dimensaoEncomenda = Integer.parseInt(info[1]);

            if (getUtilizador(utilizadores, codigoComprador) == null)
                return ErrorCode.CODIGO_INVALIDO;

            Encomenda encomenda = new Encomenda(codigo, codigoComprador, dimensaoEncomenda);
            this.encomendas.add(encomenda);
        } catch (Exception e) {
            return ErrorCode.PARAMETRO_ERRADO;
        }
        return ErrorCode.NO_ERRORS;
    }

    public ErrorCode expedirEncomenda(String info) {
        int codigo = Integer.parseInt(info);
        Encomenda encomenda = getEncomenda(encomendas, codigo);

        if (encomenda.getEstadoEncomenda() != Encomenda.PENDENTE)
            return ErrorCode.EM_EXPEDICAO;

        encomenda.setEstadoEncomenda(Encomenda.EXPEDIDA);
        encomenda.setDataEntrega(LocalDateTime.now().plusDays(7));

        return ErrorCode.NO_ERRORS;
    }

    public void entregarEncomendas() {
        for (Encomenda encomenda : encomendas) {
            if (encomenda.getEstadoEncomenda() != Encomenda.EXPEDIDA)
                continue;
            if (tempoAtual.isBefore(encomenda.getDataEntrega()))
                continue;

            Utilizador comprador = getUtilizador(utilizadores, encomenda.getCodigoComprador());
            for (Integer codigoArtigo : encomenda.getArtigos()) {
                Artigo artigo = getArtigo(artigos, codigoArtigo);
                comprador.comprarArtigo(utilizadores, artigo);
                totalFaturado += artigo.calcularPreco();
                artigos.remove(artigo);
            }
            encomenda.setEstadoEncomenda(Encomenda.FINALIZADA);
        }
    }

    public ErrorCode cancelaEncomenda(String info) {
        int codigo = Integer.parseInt(info);
        Encomenda encomenda = getEncomenda(encomendas, codigo);
        LocalDateTime dataCriacao = encomenda.getDataCriacao();

        if (dataCriacao.isAfter(dataCriacao.plusDays(Encomenda.DIAS_REEMBOLSO)))
            return ErrorCode.SEM_REEMBOLSO;
        encomendas.remove(encomenda);

        return ErrorCode.NO_ERRORS;
    }

    public ErrorCode criaUtilizador(String[] info) {
        try {
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
        } catch (Exception e) {
            return ErrorCode.PARAMETRO_ERRADO;
        }
        return ErrorCode.NO_ERRORS;
    }

    public void apagaUtilizador(String info) {
        int codigo = Integer.parseInt(info);
        Utilizador utilizador = getUtilizador(utilizadores, codigo);

        utilizador.setEmail(null);
        utilizador.setNome(null);
        utilizador.setMorada(null);
        utilizador.setNumeroFiscal(0);
        for (int codigoArtigo : utilizador.getListados()) {
            Artigo artigo = getArtigo(artigos, codigoArtigo);
            if (isArtigoInEncomendaExpedida(encomendas, artigo))
                continue;
            this.artigos.remove(artigo);
        }
        utilizador.setListados(null);
        utilizador.setAtividade(Utilizador.INATIVA);
    }

    public ErrorCode criaTransportadora(String[] info) {
        try {
            String nome = info[0];
            float margemLucro = Float.parseFloat(info[1]);
            float margemExtra = Float.parseFloat(info[2]);

            Transportadora transportadora = new Transportadora(
                    nome,
                    margemLucro,
                    margemExtra);

            this.transportadoras.add(transportadora);
        } catch (Exception e) {
            return ErrorCode.PARAMETRO_ERRADO;
        }
        return ErrorCode.NO_ERRORS;
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
        this.numVendas = 0;
        this.totalFaturado = 0;
        this.tempoAtual = LocalDateTime.now();
    }

    public Vintage(Vintage loja) {
        this.artigos = loja.getArtigos();
        this.encomendas = loja.getEncomendas();
        this.utilizadores = loja.getUtilizadores();
        this.transportadoras = loja.getTransportadoras();
        this.codigoProximoArtigo = loja.getCodigoProximoArtigo();
        this.numVendas = loja.getNumVendas();
        this.totalFaturado = loja.getTotalFaturado();
        this.tempoAtual = LocalDateTime.now();
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

    public float getTotalFaturado() {
        return totalFaturado;
    }

    public void setTotalFaturado(float totalFaturado) {
        this.totalFaturado = totalFaturado;
    }

    public int getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }

    @Override
    public String toString() {
        return "--- GERAIS ---" + "\n" +
                "Nº Total Artigos: " + (artigos.size() + numVendas) + "\n" +
                "Nº Total Utilizadores: " + utilizadores.size() + "\n" +
                "Nº Total Encomendas: " + encomendas.size() + "\n" +
                "Nº Total Transportadoras: " + transportadoras.size() + "\n" +
                "Total Faturado: " + totalFaturado + "\n" +
                "--- ARTIGOS ---" + "\n" +
                "Nº Listagens: " + artigos.size() + "\n" +
                "Nº Vendas: " + numVendas + "\n" +
                "--- UTILIZADORES ---" + "\n" +
                "Nº Ativos: " + StatsUtils.numUtilizadoresAtivos(utilizadores) + "\n" +
                "Nº Inativos: " + StatsUtils.numUtilizadoresInativos(utilizadores) + "\n" +
                "Código Maior Faturação: " + StatsUtils.utilizadorComMaiorFaturacao(utilizadores) + "\n" +
                "--- ENCOMENDAS ---" + "\n" +
                "Nº Pendentes: " + StatsUtils.numEncomendasPendentes(encomendas) + "\n" +
                "Nº Expedidas: " + StatsUtils.numEncomendasExpedidas(encomendas) + "\n" +
                "Nº Finalizadas: " + StatsUtils.numEncomendasFinalizadas(encomendas) + "\n" +
                "--- TRANSPORTADORAS ---" + "\n" +
                "Maior Valor Expedição: " + StatsUtils.transportadoraMaiorValorExpedicao(transportadoras);
    }

}
