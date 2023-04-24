package vintage.ui;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.table.Table;

import vintage.artigos.Artigo;
import vintage.utilizadores.Utilizador;

import java.util.Arrays;
import java.util.List;

public class UI {

    public static void listaUtilizadores(MultiWindowTextGUI gui, List<Utilizador> utilizadores) {
        // Create panel to hold components
        Panel panel = new Panel();

        Table<String> table = new Table<String>("Código", "Nome", "Email", "Morada", "NIF", "Vendas");
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getAtividade() == Utilizador.INATIVA) {
                continue;
            }
            
            String codigo = Integer.toString(utilizador.getCodigo());
            String nome = utilizador.getNome();
            String email = utilizador.getEmail();
            String morada = utilizador.getMorada();
            String nif = Integer.toString(utilizador.getNumeroFiscal());
            String vendas = Float.toString(utilizador.getValorEmVendas());
            table.getTableModel().addRow(codigo, nome, email, morada, nif, vendas);
        }
        table.addTo(panel);

        // Create window to hold the panel
        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);
        window.setComponent(panel);

        gui.addWindowAndWait(window);
    }

    public static void listaArtigos(MultiWindowTextGUI gui, List<Artigo> artigos){
        // Create panel to hold components
        Panel panel = new Panel();

        Table<String> table = new Table<String>("Código", "Tipo", "Marca", "Descrição", "Preço", "Nº Donos", "Estado");
        for (Artigo artigo : artigos) {
            String codigo = Integer.toString(artigo.getCodigo());
            String tipo = Integer.toString(artigo.getTipo());
            String marca = artigo.getMarca();
            String descricao = artigo.getDescricao();
            String preco = Float.toString(artigo.calcularPreco());
            String numDonos = Integer.toString(artigo.getNumDonos());
            String estadoDeUtilizacao = Float.toString(artigo.getEstadoUtilizacao());
            table.getTableModel().addRow(codigo, tipo, marca, descricao, preco, numDonos, estadoDeUtilizacao);
        }
        table.addTo(panel);

        // Create window to hold the panel
        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);
        window.setComponent(panel);

        gui.addWindowAndWait(window);
    }

    // TODO Criar funções para cada ação
}
