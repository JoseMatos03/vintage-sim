package vintage.artigos.sapatilhas;

import vintage.artigos.Artigo;

public class Sapatilhas extends Artigo {
    // Tipos de atacadores
    public static final int ATACADORES = 0;
    public static final int ATILHOS = 1;

    private int tamanho;
    private int atacadores;
    private String cor;
    private String dataLancamento;

    @Override
    public int calcularPreco() {
        // TODO calcular os descontos
        // RESTRIÇÕES:
        // Usado
        // Tamanho > 45
        return this.getPrecoBase();
    }

    public Sapatilhas(float estadoUtilizacao, int numDonos, String descricao, String marca, String codigo,
            int precoBase, int tamanho, int atacadores, String cor, String dataLancamento) {
        super(estadoUtilizacao, numDonos, descricao, marca, codigo, precoBase);

        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.dataLancamento = dataLancamento;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getAtacadores() {
        return atacadores;
    }

    public void setAtacadores(int atacadores) {
        this.atacadores = atacadores;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
