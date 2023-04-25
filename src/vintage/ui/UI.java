package vintage.ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.ComboBox.Listener;
import com.googlecode.lanterna.gui2.table.Table;

import vintage.Vintage;
import vintage.artigos.Artigo;
import vintage.transportadoras.Transportadora;
import vintage.utilizadores.Utilizador;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

// TODO Separar ficheiro
public class UI {

    public static void menu(MultiWindowTextGUI gui, BasicWindow window, Vintage loja) {
        Panel panel = new Panel();

        Button infoButton = new Button("Informação", new Runnable() {
            @Override
            public void run() {
                menuInformacao(gui, window, loja);
            }
        });
        infoButton.addTo(panel);

        Button manButton = new Button("Manutenção", new Runnable() {
            @Override
            public void run() {
                menuManutencao(gui, window, loja);
            }
        });
        manButton.addTo(panel);

        Button statsButton = new Button("Estatísticas", new Runnable() {
            @Override
            public void run() {
                // TODO Go to Estatísticas
            }
        });
        statsButton.addTo(panel);

        Button exitButton = new Button("Sair", new Runnable() {
            @Override
            public void run() {
                gui.getActiveWindow().close();
            }
        });
        exitButton.addTo(panel);

        // window.setHints(Arrays.asList(Window.Hint.CENTERED));
        // window.setCloseWindowWithEscape(true);
        window.setComponent(panel);

        gui.addWindowAndWait(window);
    }

    // INFORMAÇÃO
    public static void menuInformacao(MultiWindowTextGUI gui, BasicWindow window, Vintage loja) {
        Panel panel = new Panel();

        Button artigosButton = new Button("Ver artigos", new Runnable() {
            @Override
            public void run() {
                listaArtigos(gui, loja.getArtigos());
            }
        });
        artigosButton.addTo(panel);

        Button utilizadoresButton = new Button("Ver utilizadores", new Runnable() {
            @Override
            public void run() {
                listaUtilizadores(gui, loja.getUtilizadores());
            }
        });
        utilizadoresButton.addTo(panel);

        Button transportadorasButton = new Button("Ver transportadoras", new Runnable() {
            @Override
            public void run() {
                listaTransportadoras(gui, loja.getTransportadoras());
            }
        });
        transportadorasButton.addTo(panel);

        Button goBackButton = new Button("Voltar", new Runnable() {
            @Override
            public void run() {
                menu(gui, window, loja);
            }
        });
        goBackButton.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    public static void listaArtigos(MultiWindowTextGUI gui, List<Artigo> artigos) {
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

    public static void listaTransportadoras(MultiWindowTextGUI gui, List<Transportadora> transportadoras) {
        // Create panel to hold components
        Panel panel = new Panel();

        Table<String> table = new Table<String>("Nome", "Margem Lucro", "Margem Extra", "Valor de Expedição");
        for (Transportadora transportadora : transportadoras) {
            String nome = transportadora.getNome();
            String margemLucro = Float.toString(transportadora.getMargemLucro());
            String margemExtra = Float.toString(transportadora.getMargemExtra());
            String valorExpedicao = Float.toString(transportadora.getValorExpedicao());
            table.getTableModel().addRow(nome, margemLucro, margemExtra, valorExpedicao);
        }
        table.addTo(panel);

        // Create window to hold the panel
        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);
        window.setComponent(panel);

        gui.addWindowAndWait(window);
    }

    // MANUTENÇÃO
    public static void menuManutencao(MultiWindowTextGUI gui, BasicWindow window, Vintage loja) {
        Panel panel = new Panel();

        Button artigosButton = new Button("Criar artigo", new Runnable() {
            @Override
            public void run() {
                criarArtigo(gui, loja);
            }
        });
        artigosButton.addTo(panel);

        // TODO Criar encomenda

        Button utilizadoresButton = new Button("Criar utilizadores", new Runnable() {
            @Override
            public void run() {

            }
        });
        utilizadoresButton.addTo(panel);

        Button transportadorasButton = new Button("Criar transportadoras", new Runnable() {
            @Override
            public void run() {

            }
        });
        transportadorasButton.addTo(panel);

        Button goBackButton = new Button("Voltar", new Runnable() {
            @Override
            public void run() {
                menu(gui, window, loja);
            }
        });
        goBackButton.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    public static void criarArtigo(MultiWindowTextGUI gui, Vintage loja) {
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        // TIPO
        panel.addComponent(new Label("Tipo"));
        final ComboBox<String> tipo = new ComboBox<String>();
        tipo.addItem("Mala");
        tipo.addItem("Sapatilhas");
        tipo.addItem("T-Shirt");
        tipo.setPreferredSize(new TerminalSize(20, 1));
        panel.addComponent(tipo);

        // CÓDIGO VENDEDOR
        panel.addComponent(new Label("Código Vendedor"));
        final TextBox codigoVendedor = new TextBox(new TerminalSize(20, 1))
                .setValidationPattern(Pattern.compile("[0-9]*")).addTo(panel);

        // ESTADO
        panel.addComponent(new Label("Estado"));
        final ComboBox<String> estado = new ComboBox<String>();
        estado.addItem("Sem uso");
        estado.addItem("Pouco uso");
        estado.addItem("Muito uso");
        estado.addItem("Estragado");
        panel.addComponent(estado);

        // Nº DONOS
        panel.addComponent(new Label("Nº Donos"));
        final TextBox numDonos = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(panel);

        // DESCRIÇÃO
        panel.addComponent(new Label("Descrição"));
        final TextBox descicao = new TextBox().addTo(panel);

        // MARCA
        panel.addComponent(new Label("Marca"));
        final TextBox marca = new TextBox().addTo(panel);

        // PREÇO BASE
        panel.addComponent(new Label("Preço base"));
        final TextBox precoBase = new TextBox().setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"))
                .addTo(panel);

        // TRANSPORTADORA
        panel.addComponent(new Label("Transportadora"));
        final TextBox transportadora = new TextBox().addTo(panel);

        // --- MALA ---
        // DIMENSÕES
        final Label comprimentoLabel = new Label("Comprimento").addTo(panel);
        final TextBox comprimento = new TextBox().setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"))
                .addTo(panel);
        final Label larguraLabel = new Label("Largura").addTo(panel);
        final TextBox largura = new TextBox().setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"))
                .addTo(panel);
        final Label alturaLabel = new Label("Altura").addTo(panel);
        final TextBox altura = new TextBox().setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"))
                .addTo(panel);
        // MATERIAL
        final Label materialLabel = new Label("Material").addTo(panel);
        final ComboBox<String> material = new ComboBox<String>();
        material.addItem("Tecido");
        material.addItem("Pele");
        material.addItem("Lona");
        material.addItem("Veludo");
        material.addTo(panel);
        // ANO COLEÇÃO
        final Label anoColecaoLabel = new Label("Ano Coleção").addTo(panel);
        final TextBox anoColecao = new TextBox().setValidationPattern(Pattern.compile("^[0-9]{0,4}?$")).addTo(panel);

        // --- SAPATILHAS ---
        // TAMANHO
        final Label tamanhoLabel = new Label("Tamanho");
        final TextBox tamanho = new TextBox().setValidationPattern(Pattern.compile("^[0-9]{0,2}?$"));
        // ATACADORES
        final Label atacadoresLabel = new Label("Material");
        final ComboBox<String> atacadores = new ComboBox<String>();
        atacadores.addItem("Atacadores");
        atacadores.addItem("Atilhos");

        // --- TSHIRT ---
        // TAMANHO
        final Label tamanhoTShirtLabel = new Label("Tamanho");
        final ComboBox<String> tamanhoTShirt = new ComboBox<String>();
        tamanhoTShirt.addItem("S");
        tamanhoTShirt.addItem("M");
        tamanhoTShirt.addItem("L");
        tamanhoTShirt.addItem("XL");
        // PADRÃO
        final Label padraoLabel = new Label("Padrão");
        final ComboBox<String> padrao = new ComboBox<String>();
        padrao.addItem("Liso");
        padrao.addItem("Riscos");
        padrao.addItem("Palmeira");

        Button confirmButton = new Button("Confirmar", new Runnable() {
            @Override
            public void run() {
                // TODO Extrair
                String estadoUtilizacao = "0";
                switch (estado.getText()) {
                    case "Sem uso":
                        estadoUtilizacao = "1.00";
                        break;

                    case "Pouco uso":
                        estadoUtilizacao = "0.75";
                        break;

                    case "Muito uso":
                        estadoUtilizacao = "0.50";
                        break;
                    case "Estragado":
                        estadoUtilizacao = "0.25";
                        break;
                    default:
                        break;
                }
                if (tipo.getSelectedItem().equals("Mala")) {
                    String materialMala = null;
                    if (material.getText().equals("Tecido")) {
                        materialMala = "0";
                    } else if (material.getText().equals("Pele")) {
                        materialMala = "1";
                    } else if (material.getText().equals("Lona")) {
                        materialMala = "2";
                    } else if (material.getText().equals("Veludo")) {
                        materialMala = "3";
                    }
                    loja.criaArtigo(new String[] {
                            "0",
                            codigoVendedor.getText(),
                            estadoUtilizacao,
                            numDonos.getText(),
                            descicao.getText(),
                            marca.getText(),
                            precoBase.getText(),
                            transportadora.getText(),
                            comprimento.getText(),
                            largura.getText(),
                            altura.getText(),
                            materialMala,
                            anoColecao.getText()
                    });
                }
            }
        });
        confirmButton.addTo(panel);

        tipo.addListener(new Listener() {
            @Override
            public void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction) {
                panel.removeComponent(comprimentoLabel);
                panel.removeComponent(comprimento);
                panel.removeComponent(larguraLabel);
                panel.removeComponent(largura);
                panel.removeComponent(alturaLabel);
                panel.removeComponent(altura);
                panel.removeComponent(materialLabel);
                panel.removeComponent(material);
                panel.removeComponent(anoColecaoLabel);
                panel.removeComponent(anoColecao);
                panel.removeComponent(tamanhoLabel);
                panel.removeComponent(tamanho);
                panel.removeComponent(atacadoresLabel);
                panel.removeComponent(atacadores);
                panel.removeComponent(tamanhoTShirtLabel);
                panel.removeComponent(tamanhoTShirt);
                panel.removeComponent(padraoLabel);
                panel.removeComponent(padrao);
                panel.removeComponent(confirmButton);
                if (selectedIndex == 0) {
                    comprimentoLabel.addTo(panel);
                    comprimento.addTo(panel);
                    larguraLabel.addTo(panel);
                    largura.addTo(panel);
                    alturaLabel.addTo(panel);
                    altura.addTo(panel);
                    materialLabel.addTo(panel);
                    material.addTo(panel);
                    anoColecaoLabel.addTo(panel);
                    anoColecao.addTo(panel);
                } else if (selectedIndex == 1) {
                    tamanhoLabel.addTo(panel);
                    tamanho.addTo(panel);
                    atacadoresLabel.addTo(panel);
                    atacadores.addTo(panel);
                } else if (selectedIndex == 2) {
                    tamanhoTShirtLabel.addTo(panel);
                    tamanhoTShirt.addTo(panel);
                    padraoLabel.addTo(panel);
                    padrao.addTo(panel);
                }
                panel.addComponent(confirmButton);
            }
        });

        // Criar window
        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);
        window.setComponent(panel);

        gui.addWindowAndWait(window);
    }

    // TODO Criar funções para cada ação
}
