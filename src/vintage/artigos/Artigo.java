package vintage.artigos;

public abstract class Artigo {
    public String descricao;
    public int preco;

    public abstract int calcularPreco();
}
