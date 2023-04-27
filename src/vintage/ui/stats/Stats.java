package vintage.ui.stats;

import java.util.Arrays;
import java.util.List;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.table.Table;

import vintage.ui.UI;
import vintage.Vintage;
import vintage.utilizadores.Utilizador;
import vintage.transportadoras.Transportadora;
import vintage.encomendas.Encomenda;

public class Stats {

    public static void menuStats(MultiWindowTextGUI gui, BasicWindow window, Vintage loja)
    {
        Panel panel = new Panel();

        Button vendedorButton = new Button("Vendedor com mais ganhos", new Runnable() {
            @Override
            public void run() {
                Utilizador vendedor = vendedorMaiorFaturacao(loja);
                displayVendedor(gui, loja, vendedor);
            }
        });
        vendedorButton.addTo(panel);

        Button transportadoraMaisGanhos = new Button("Transportadora com maior volume", new Runnable() {
            @Override
            public void run() {
                Transportadora transportadora = transportadoraMaiorFaturacao(loja);
                displayTransportadora(gui, transportadora);
            }
        });
        transportadoraMaisGanhos.addTo(panel);

        // TODO Botão para o ponto 3 das estatísticas do enunciado

        // TODO Botão para o ponto 4 das estatísticas do enunciado

        Button ganhosLoja = new Button("Ganhos Totais", new Runnable() {
            @Override
            public void run() {
                float totalLoja = totalGanho(loja);
                displayGanhosLoja(gui, totalLoja);
            }
        });
        ganhosLoja.addTo(panel);

        Button goBackButton = new Button("Voltar", new Runnable() {
            @Override
            public void run() {
                UI.menu(gui, window, loja);
            }
        });
        goBackButton.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    public static Utilizador vendedorMaiorFaturacao(Vintage vintage) // TODO Implementar intervalos de tempo
    {
        float valorVendas = 0;
        List<Utilizador> utilizadores = vintage.getUtilizadores();
        Utilizador utilizadorMaisGanhos = utilizadores.get(0);
        for(Utilizador utilizador : utilizadores)
        {
            float valorVendasAtual = utilizador.getValorEmVendas();
            if (valorVendasAtual > valorVendas) {
                valorVendas = valorVendasAtual;
                utilizadorMaisGanhos = utilizador;
            }
        }
        return utilizadorMaisGanhos;
    }

    public static void displayVendedor(MultiWindowTextGUI gui, Vintage loja, Utilizador vendedor)
    {
        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();

        Table<String> table = new Table<String>("Código de Utilizador", "Nome", "Ganhos");
        String codigo = Integer.toString(vendedor.getCodigo());
        String nome = vendedor.getNome();
        String ganhos = Float.toString(vendedor.getValorEmVendas());

        table.getTableModel().addRow(codigo, nome, ganhos);

        table.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }
    
    public static Transportadora transportadoraMaiorFaturacao(Vintage vintage)
    {
        float maiorFaturacao = 0;
        List<Transportadora> transportadoras = vintage.getTransportadoras();
        Transportadora transportadoraMaior = transportadoras.get(0);
        for(Transportadora transportadora : transportadoras)
        {
            float valorExpedicao = transportadora.getValorExpedicao();
            if (valorExpedicao > maiorFaturacao) {
                maiorFaturacao = valorExpedicao;
                transportadoraMaior = transportadora;
            }
        }
        return transportadoraMaior;
    }

    public static void displayTransportadora(MultiWindowTextGUI gui, Transportadora transportadora)
    {
        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();

        Table<String> table = new Table<String>("Nome da Transportadora", "Valor");
        String nome = transportadora.getNome();
        String valor = Float.toString(transportadora.getValorExpedicao());

        table.getTableModel().addRow(nome, valor);

        table.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    // TODO Ponto 3 das estatísticas do enunciado

    // TODO Ponto 4 das estatísticas do enunciado


    public static float totalGanho(Vintage vintage)
    {
        List<Encomenda> encomendas = vintage.getEncomendas();
        float ganhos = 0;
        for (Encomenda encomenda : encomendas) {
            ganhos += encomenda.getPrecoEncomenda();
        }
        return ganhos;
    }

    public static void displayGanhosLoja(MultiWindowTextGUI gui, float totalLoja)
    {
        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();

        Table<String> table = new Table<String>("Ganhos Totais");
        table.getTableModel().addRow(Float.toString(totalLoja));

        table.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }
}
