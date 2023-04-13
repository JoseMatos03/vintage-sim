package test.artigos.mala;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import vintage.artigos.mala.Mala;

public class MalaTest {

    // Malas-Tipo a testar
    private Mala malaTeste_um = new Mala(
            1f,
            0,
            "Esta mala da Louis Vuitton é a escolha perfeita para quem busca estilo, funcionalidade e qualidade em um único produto. Feita com materiais de alta qualidade e cuidadosamente projetada para atender às necessidades dos viajantes modernos, esta mala combina praticidade com um design elegante e sofisticado.",
            "Louis Vuitton",
            159.99f,
            new float[] { 15f, 10f, 10f },
            Mala.LONA,
            2022);

    private Mala malaTeste_dois = new Mala(
            0.7f,
            4,
            "Esta mala da Louis Vuitton é a escolha perfeita para quem busca estilo, funcionalidade e qualidade em um único produto. Feita com materiais de alta qualidade e cuidadosamente projetada para atender às necessidades dos viajantes modernos, esta mala combina praticidade com um design elegante e sofisticado.",
            "Louis Vuitton",
            59.99f,
            new float[] { 10f, 7.5f, 7f },
            Mala.TECIDO,
            2019);

    @Test
    public void dadasDimensoes_quandoCalcularDimensao_retornaDimensao() {
        float resultadoTeste_um = malaTeste_um.calcularDimensao();
        assertEquals(1500f, resultadoTeste_um, 0.1f);

        float resultadoTeste_dois = malaTeste_dois.calcularDimensao();
        assertEquals(525f, resultadoTeste_dois, 0.1f);
    }
}
