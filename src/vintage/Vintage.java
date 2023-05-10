package vintage;

import static vintage.utils.encomendas.Utils.parseTempoExpedicao;
import static vintage.utils.vintage.Utils.FORMATTER;
import static vintage.utils.vintage.Utils.getArtigo;
import static vintage.utils.vintage.Utils.getEncomenda;
import static vintage.utils.vintage.Utils.getEncomendaOfArtigo;
import static vintage.utils.vintage.Utils.getTransportadora;
import static vintage.utils.vintage.Utils.getUtilizador;
import static vintage.utils.vintage.Utils.isArtigoInEncomendaExpedida;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import vintage.artigos.Artigo;
import vintage.artigos.mala.Mala;
import vintage.artigos.sapatilhas.Sapatilhas;
import vintage.artigos.tshirt.TShirt;
import vintage.encomendas.Encomenda;
import vintage.transportadoras.Transportadora;
import vintage.utilizadores.Utilizador;
import vintage.utils.ErrorCode;
import vintage.utils.artigos.Utils;
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
        this.tempoAtual = loja.getTempoAtual();
    }

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

            if(getUtilizador(utilizadores, codigoVendedor).getAtividade() == Utilizador.INATIVA)
                return ErrorCode.UTILIZADOR_INATIVO;

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
                    boolean premiumMala = Boolean.parseBoolean(info[13]);

                    if (premiumMala && !transportadora.getPremiumEstado())
                        return ErrorCode.TRANSPORTADORA_INVALIDA;

                    if (Mala.calcularDimensao(comprimento, largura, altura) <= Mala.CONSTANTE_CORRECAO - Mala.MARGEM_ERRO)
                        return ErrorCode.DIMENSOES_INVALIDAS;

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
                            transportadora,
                            premiumMala);

                    this.artigos.add(mala);
                    getUtilizador(utilizadores, codigoVendedor).criarListagem(mala);
                    break;

                case Artigo.SAPATILHAS:
                    int tamanhoSapatilhas = Integer.parseInt(info[8]);
                    int atacadores = Integer.parseInt(info[9]);
                    String cor = info[10];
                    int anoColecaoSapatilhas = Integer.parseInt(info[11]);
                    boolean premiumSapatilha = Boolean.parseBoolean(info[12]);

                    if (premiumSapatilha && !transportadora.getPremiumEstado())
                        return ErrorCode.TRANSPORTADORA_INVALIDA;

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
                            transportadora,
                            premiumSapatilha);

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

            if(getUtilizador(utilizadores, codigoComprador).getAtividade() == Utilizador.INATIVA)
                return ErrorCode.UTILIZADOR_INATIVO;

            Encomenda encomenda = new Encomenda(codigo, codigoComprador, dimensaoEncomenda, tempoAtual);
            this.encomendas.add(encomenda);
        } catch (Exception e) {
            return ErrorCode.PARAMETRO_ERRADO;
        }
        return ErrorCode.NO_ERRORS;
    }

    public ErrorCode adicionarArtigoEmEncomenda(String... info) {
        ErrorCode error;
        try {
            int codigoEncomenda = Integer.parseInt(info[0]);
            int codigoArtigo = Integer.parseInt(info[1]);
            Encomenda encomenda = getEncomenda(encomendas, codigoEncomenda);

            error = encomenda.adicionarArtigo(artigos, encomendas, codigoArtigo);
        } catch (Exception e) {
            return ErrorCode.CODIGO_INVALIDO;
        }
        return error;
    }

    public ErrorCode removerArtigoEmEncomenda(String... info) {
        ErrorCode error;
        try {
            int codigoEncomenda = Integer.parseInt(info[0]);
            int codigoArtigo = Integer.parseInt(info[1]);
            Encomenda encomenda = getEncomenda(encomendas, codigoEncomenda);

            error = encomenda.removerArtigo(artigos, codigoArtigo);
        } catch (Exception e) {
            return ErrorCode.CODIGO_INVALIDO;
        }
        return error;
    }

    public ErrorCode expedirEncomenda(String info) {
        int codigo = Integer.parseInt(info);
        Encomenda encomenda = getEncomenda(encomendas, codigo);

        if (encomenda.getEstadoEncomenda() != Encomenda.PENDENTE)
            return ErrorCode.EM_EXPEDICAO;

        encomenda.setEstadoEncomenda(Encomenda.EXPEDIDA);
        encomenda.setDataEntrega(tempoAtual.plusDays(parseTempoExpedicao(encomenda)));

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
                ++numVendas;

                getTransportadora(transportadoras, artigo.getTransportadora().getNome())
                        .calcularEntrega(artigo.getPrecoBase());
                artigos.remove(artigo);
            }
            encomenda.setEstadoEncomenda(Encomenda.FINALIZADA);
        }
    }

    public ErrorCode cancelaEncomenda(String info) {
        int codigo = Integer.parseInt(info);
        Encomenda encomenda = getEncomenda(encomendas, codigo);
        LocalDateTime dataCriacao = encomenda.getDataCriacao();

        if (tempoAtual.isAfter(dataCriacao.plusDays(Encomenda.DIAS_REEMBOLSO)))
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

            String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern = Pattern.compile(emailRegex);

            if (!pattern.matcher(email).matches())
                return ErrorCode.EMAIL_INVALIDO;

            if (info[3].length() != 9)
                return ErrorCode.NIF_INVALIDO;

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
            boolean premiumTransportadora = Boolean.parseBoolean(info[3]);

            Transportadora transportadora = new Transportadora(
                    nome,
                    margemLucro,
                    margemExtra,
                    premiumTransportadora);

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

    public ErrorCode timeTravel(String info) {
        try {
            this.setTempoAtual(LocalDateTime.parse(info, FORMATTER));
        } catch (DateTimeParseException e) {
            return ErrorCode.DATA_INVALIDA;
        }
        this.entregarEncomendas();
        return ErrorCode.NO_ERRORS;
    }

    public void wipeAll() {
        this.artigos = new ArrayList<>();
        this.encomendas = new ArrayList<>();
        this.utilizadores = new ArrayList<>();
        this.transportadoras = new ArrayList<>();
        this.codigoProximoArtigo = 0;
        this.numVendas = 0;
        this.totalFaturado = 0;
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

    public LocalDateTime getTempoAtual() {
        return tempoAtual;
    }

    public void setTempoAtual(LocalDateTime tempoAtual) {
        this.tempoAtual = tempoAtual;
    }

    @Override
    public String toString() {
        return "--- GERAIS ---" + "\n" +
                "Nº Total Artigos: " + (artigos.size() + numVendas) + "\n" +
                "Nº Total Utilizadores: " + utilizadores.size() + "\n" +
                "Nº Total Encomendas: " + encomendas.size() + "\n" +
                "Nº Total Transportadoras: " + transportadoras.size() + "\n" +
                "Total Faturado: " + Utils.arrondarCentesimas(totalFaturado) + "\n" +
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
                "Maior Lucro: " + StatsUtils.transportadoraMaiorLucro(transportadoras) + "\n" +
                "Maior Valor Expedição: " + StatsUtils.transportadoraMaiorValorExpedicao(transportadoras);

    }

}
