package vintage.encomendas;

import java.time.LocalDateTime;
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

    private List<Artigo> artigos;
    private int dimensaoEncomenda;
    private int estadoEncomenda;

    private float precoEncomenda;
    private LocalDateTime dataCriacao;

    public void adicionarArtigos(Artigo artigo) {
        this.artigos.add(artigo);  
        float novoPrecoEncomenda = this.precoEncomenda + artigo.calcularPreco();
        this.setPrecoEncomenda(novoPrecoEncomenda);
        // TODO Analisar tamanho da encomenda
    }

    public void removerArtigo(Artigo artigo) {
        this.artigos.remove(artigo);  
        float novoPrecoEncomenda = this.precoEncomenda - artigo.calcularPreco();
        this.setPrecoEncomenda(novoPrecoEncomenda);
        // TODO Analisar tamanho da encomenda
    }

    public float calcularPrecoFinal() {
        float precoEncomenda = 0;

        for (int i = 0; i < this.getArtigos().size(); i++) {
            precoEncomenda += this.artigos.get(i).calcularPreco();
        }

        return precoEncomenda;
    }

    // TODO Reembolso após funcionalidade de tempo implementada
    public float reembolso() 
    {
        return 0;
    }
    
    public Encomenda(List<Artigo> artigos, int dimensaoEncomenda) {
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
}
