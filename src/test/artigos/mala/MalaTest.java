package test.artigos.mala;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import vintage.artigos.mala.Mala;
import static vintage.artigos.mala.Mala.LONA;
import static vintage.artigos.mala.Mala.TECIDO;
import static vintage.artigos.mala.Mala.PELE;
import static vintage.artigos.mala.Mala.VELUDO;

public class MalaTest {

    // Malas-Tipo a testar
    private Mala malaTeste_um = new Mala(
            1f,
            0,
            "Esta mala da Louis Vuitton é a escolha perfeita para quem busca estilo, funcionalidade e qualidade em um único produto. Feita com materiais de alta qualidade e cuidadosamente projetada para atender às necessidades dos viajantes modernos, esta mala combina praticidade com um design elegante e sofisticado.",
            "Louis Vuitton",
            159.99f,
            new float[] { 15f, 10f, 10f },
            LONA,
            2022);

    private Mala malaTeste_dois = new Mala(
            0.7f,
            4,
            "Esta mala da Louis Vuitton é a escolha perfeita para quem busca estilo, funcionalidade e qualidade em um único produto. Feita com materiais de alta qualidade e cuidadosamente projetada para atender às necessidades dos viajantes modernos, esta mala combina praticidade com um design elegante e sofisticado.",
            "Louis Vuitton",
            59.99f,
            new float[] { 10f, 7.5f, 7f },
            TECIDO,
            2019);

    private Mala malaTeste_tres = new Mala(
            0.85f,
            2,
            "Esta mala da Louis Vuitton é a escolha perfeita para quem busca estilo, funcionalidade e qualidade em um único produto. Feita com materiais de alta qualidade e cuidadosamente projetada para atender às necessidades dos viajantes modernos, esta mala combina praticidade com um design elegante e sofisticado.",
            "Louis Vuitton",
            "ABCD",
            20.00f,
            new float[] { 12.5f, 5.5f, 10f },
            PELE,
            2021);

    private Mala malaTeste_quatro = new Mala(
            0.5f,
            7,
            "Esta mala da Louis Vuitton é a escolha perfeita para quem busca estilo, funcionalidade e qualidade em um único produto. Feita com materiais de alta qualidade e cuidadosamente projetada para atender às necessidades dos viajantes modernos, esta mala combina praticidade com um design elegante e sofisticado.",
            "Louis Vuitton",
            "ABCD",
            10.50f,
            new float[] { 10.5f, 2f, 10f },
            VELUDO,
            2014);

    @Test
    public void dadasDimensoes_quandoCalcularDimensao_retornaDimensao() {
        float resultadoTeste_um = malaTeste_um.calcularDimensao();
        assertEquals(1500f, resultadoTeste_um, 0.1f);

        float resultadoTeste_dois = malaTeste_dois.calcularDimensao();
        assertEquals(525f, resultadoTeste_dois, 0.1f);

        float resultadoTeste_tres = malaTeste_tres.calcularDimensao();
        assertEquals(687.5f, resultadoTeste_tres, 0.1f);

        float resultadoTeste_quatro = malaTeste_quatro.calcularDimensao();
        assertEquals(210f, resultadoTeste_quatro, 0.1f);
    }

    @Test
    public void dadoDimensao_quandoCalcularCorrecao_retornaCorrecao() {
        float resultadoTeste_um = malaTeste_um.calcularCorrecao();
        assertEquals(-5.33f, resultadoTeste_um, 0.01f);

        float resultadoTeste_dois = malaTeste_dois.calcularCorrecao();
        assertEquals(-5.76f, resultadoTeste_dois, 0.01f);

        float resultadoTeste_tres = malaTeste_tres.calcularCorrecao();
        assertEquals(-1.45f, resultadoTeste_tres, 0.01f);

        float resultadoTeste_quatro = malaTeste_quatro.calcularCorrecao();
        assertEquals(-2.50f, resultadoTeste_quatro, 0.01f);
    }

    @Test
    public void dadoPrecoBaseEPrecoCorrecao_quandoCalcularPreco_retornaPrecoFinal() {
        float resultadoTeste_um = malaTeste_um.calcularPreco();
        assertEquals(154.66f, resultadoTeste_um, 0.01f);

        float resultadoTeste_dois = malaTeste_dois.calcularPreco();
        assertEquals(54.74f, resultadoTeste_dois, 0.01f);

        float resultadoTeste_tres = malaTeste_tres.calcularPreco();
        assertEquals(18.55f, resultadoTeste_tres, 0.01f);

        float resultadoTeste_quatro = malaTeste_quatro.calcularPreco();
        assertEquals(8f, resultadoTeste_quatro, 0.01f);
    }
}
