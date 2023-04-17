package test.artigos.sapatilhas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import vintage.artigos.sapatilhas.Sapatilhas;
import vintage.transportadoras.Transportadora;

import static vintage.artigos.sapatilhas.Sapatilhas.ATACADORES;
import static vintage.artigos.sapatilhas.Sapatilhas.ATILHOS;

public class SapatilhasTest {

    // Transportadoras
    Transportadora transportadoraTeste_um = new Transportadora(5.00f);
    Transportadora transportadoraTeste_dois = new Transportadora(2.00f, 1.20f);

    // Sapatilhas-Tipo a testar
    private Sapatilhas sapatilhasTeste_um = new Sapatilhas(
            0.85f,
            2,
            "Sapatilhas",
            "Adidas",
            55.25f,
            39,
            ATACADORES,
            "Branco",
            2022,
            transportadoraTeste_dois);

    private Sapatilhas sapatilhasTeste_dois = new Sapatilhas(
            0.65f,
            5,
            "Sapatilhas",
            "Nike",
            26.00f,
            35,
            ATILHOS,
            "Preto",
            2018,
            transportadoraTeste_um);

    @Test
    public void dadoPrecoBaseEPrecoCorrecao_quandoCalcularPreco_retornaPrecoFinal() {
        float resultadoTeste_um = sapatilhasTeste_um.calcularPreco();
        assertEquals(46.96f, resultadoTeste_um, 0.01f);

    }
}
