package vintage.encomendas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import vintage.artigos.Artigo;

public class Encomenda {
    private List<Artigo> artigos;

    public static final int GRANDE = 0;
    public static final int MEDIO = 1;
    public static final int PEQUENO = 2;

    public static final int PENDENTE = 0;
    public static final int EXPEDIDA = 1;
    public static final int FINALIZADA = 2;

    private int dimensaoEncomenda;
    private int estadoEncomenda;

    private double precoFinal;
    private LocalDate dataCriacao;

    public Encomenda(List<Artigo> artigos)
    {
        this.artigos = new ArrayList<>(artigos);
        this.dimensaoEncomenda = artigos.size();
        this.estadoEncomenda = PENDENTE; // TODO Atualizar estado da encomenda quando for implementadp tempo
        this.precoFinal = this.calcularPrecoFinal();
        this.dataCriacao = LocalDate.now();
    }

    public int getDimensao()
    {
        return this.dimensaoEncomenda;
    }

    public int getEstado()
    {
        return this.estadoEncomenda;
    }
    public void setEstado(int estado)
    {
        this.estadoEncomenda = estado;
    }

    public LocalDate getDataCriacao()
    {
        return dataCriacao;
    }

    public void adicionarArtigos(Artigo artigo)
    {
        this.artigos.add(artigo);
        this.precoFinal += artigo.calcularPreco();
        dimensaoEncomenda++;
    }

    public void removerArtigos(Artigo artigo)
    {
        this.artigos.remove(artigo);
        this.precoFinal -= artigo.calcularPreco();
        dimensaoEncomenda--;
    }

    public double calcularPrecoFinal()
    {
        double precoFinal = 0;
        for (int i = 0; i < this.dimensaoEncomenda; i++) {
            precoFinal += artigos.get(i).calcularPreco();
        }
        return precoFinal;
    }

    public double reembolso() // TODO Reembolso quando houver tempo a funcionar
    {
        return this.precoFinal;
    }
}
