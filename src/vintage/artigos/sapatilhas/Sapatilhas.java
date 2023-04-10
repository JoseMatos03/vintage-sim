package vintage.artigos.sapatilhas;

import vintage.artigos.Artigo;

public class Sapatilhas extends Artigo {

    public Sapatilhas()
    {
        this.descricao = "Sapatilha";
        this.preco = 2;
    }
    
    
    @Override
    public int calcularPreco() {
        return this.preco;
    }
}
