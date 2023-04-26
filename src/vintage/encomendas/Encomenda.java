package vintage.encomendas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import vintage.artigos.Artigo;

public class Encomenda {

    // Tamanho da encomenda
    public static final int GRANDE = 0;
    public static final int MEDIO = 1;
    public static final int PEQUENO = 2;

    // Estado da encomenda
    public static final int PENDENTE = 0;
    public static final int EXPEDIDA = 1;
    public static final int FINALIZADA = 2;

    private int codigo;
    private List<Artigo> artigos;
    private int dimensaoEncomenda;
    private int estadoEncomenda;

    private float precoEncomenda;
    private LocalDateTime dataCriacao;

    public void adicionarArtigos(Artigo artigo) {
        if (this.artigos.size() >= this.dimensaoEncomenda)
            return;

        this.artigos.add(artigo);
        float novoPrecoEncomenda = this.precoEncomenda + artigo.calcularPreco();
        this.setPrecoEncomenda(novoPrecoEncomenda);
    }

    public void removerArtigo(Artigo artigo) {
        if (this.artigos.isEmpty())
            return;

        this.artigos.remove(artigo);
        float novoPrecoEncomenda = this.precoEncomenda - artigo.calcularPreco();
        this.setPrecoEncomenda(novoPrecoEncomenda);
    }

    public float calcularPrecoFinal() {
        float precoEncomenda = 0;

        for (int i = 0; i < this.getArtigos().size(); i++) {
            precoEncomenda += this.artigos.get(i).calcularPreco();
        }

        return precoEncomenda;
    }

    public void reembolsar() {
        return;
    }

    public Encomenda(int codigo, int dimensaoEncomenda) {
        this.codigo = codigo;
        this.artigos = new ArrayList<>();
        this.dimensaoEncomenda = dimensaoEncomenda;
        this.estadoEncomenda = PENDENTE;
        this.precoEncomenda = 0;
        this.dataCriacao = LocalDateTime.now();
    }

    public Encomenda(int codigo, List<Artigo> artigos, int dimensaoEncomenda) {
        this.codigo = codigo;
        this.artigos = artigos;
        this.dimensaoEncomenda = dimensaoEncomenda;
        this.estadoEncomenda = PENDENTE; // TODO Atualizar estado da encomenda conforme passagem de tempo
        this.precoEncomenda = this.calcularPrecoFinal();
        this.dataCriacao = LocalDateTime.now();
    }

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }

    public int getDimensaoEncomenda() {
        return dimensaoEncomenda;
    }

    public void setDimensaoEncomenda(int dimensaoEncomenda) {
        this.dimensaoEncomenda = dimensaoEncomenda;
    }

    public int getEstadoEncomenda() {
        return estadoEncomenda;
    }

    public void setEstadoEncomenda(int estadoEncomenda) {
        this.estadoEncomenda = estadoEncomenda;
    }

    public float getPrecoEncomenda() {
        return precoEncomenda;
    }

    public void setPrecoEncomenda(float precoFinal) {
        this.precoEncomenda = precoFinal;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
