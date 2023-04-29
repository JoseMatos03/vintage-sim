package vintage.ui.manage;

import java.util.Arrays;
import java.util.regex.Pattern;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.ComboBox.Listener;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;

import vintage.Vintage;
import vintage.artigos.Artigo;
import vintage.ui.UI;
import vintage.utils.ui.ManageUtils;

// TODO corrigir bugs
// Se preencher com um campo vazio, o programa crasha
// Impedir de adicionar códigos não existentes
public class Manage {

    public static void menuManutencao(MultiWindowTextGUI gui, BasicWindow window, Vintage loja) {
        Panel panel = new Panel();

        new Button("Criar artigo", new Runnable() {
            @Override
            public void run() {
                criarArtigo(gui, loja);
            }
        }).addTo(panel);

        new Button("Criar utilizadores", new Runnable() {
            @Override
            public void run() {
                criarUtilizador(gui, loja);
            }
        }).addTo(panel);

        new Button("Criar encomendas", new Runnable() {
            @Override
            public void run() {
                criarEncomenda(gui, loja);
            }
        }).addTo(panel);

        new Button("Criar transportadoras", new Runnable() {
            @Override
            public void run() {
                criarTransportadora(gui, loja);
            }
        }).addTo(panel);

        new Button("Voltar", new Runnable() {
            @Override
            public void run() {
                UI.menu(gui, window, loja);
            }
        }).addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    public static void criarArtigo(MultiWindowTextGUI gui, Vintage loja) {

        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        final TerminalSize size = new TerminalSize(20, 1);

        // TIPO
        final Label tipoLabel = new Label("Tipo");
        final ComboBox<String> tipo = new ComboBox<String>();
        tipo.addItem("Mala");
        tipo.addItem("Sapatilhas");
        tipo.addItem("T-Shirt");
        tipo.setPreferredSize(size);

        // CÓDIGO VENDEDOR
        final Label codigoVendedorLabel = new Label("Código Vendedor");
        final TextBox codigoVendedor = new TextBox(size).setValidationPattern(Pattern.compile("[0-9]*"));

        // ESTADO
        final Label estadoUtilizacaoLabel = new Label("Estado");
        final ComboBox<String> estadoUtilizacao = new ComboBox<String>();
        estadoUtilizacao.addItem("Sem uso");
        estadoUtilizacao.addItem("Pouco uso");
        estadoUtilizacao.addItem("Muito uso");
        estadoUtilizacao.addItem("Estragado");
        estadoUtilizacao.setPreferredSize(size);

        // Nº DONOS
        final Label numDonosLabel = new Label("Nº Donos");
        final TextBox numDonos = new TextBox(size).setValidationPattern(Pattern.compile("[0-9]*"));

        // DESCRIÇÃO
        final Label descricaoLabel = new Label("Descrição");
        final TextBox descricao = new TextBox(size);

        // MARCA
        final Label marcaLabel = new Label("Marca");
        final TextBox marca = new TextBox(size);

        // PREÇO BASE
        final Label precoBaseLabel = new Label("Preço Base");
        final TextBox precoBase = new TextBox(size).setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"));

        // TRANSPORTADORA
        final Label transportadoraLabel = new Label("Transportadora");
        final TextBox transportadora = new TextBox(size);

        // --- MALA ---
        // DIMENSÕES
        final Label comprimentoLabel = new Label("Comprimento");
        final TextBox comprimento = new TextBox(size)
                .setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"));
        final Label larguraLabel = new Label("Largura");
        final TextBox largura = new TextBox(size).setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"));
        final Label alturaLabel = new Label("Altura");
        final TextBox altura = new TextBox(size).setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"));
        // MATERIAL
        final Label materialLabel = new Label("Material");
        final ComboBox<String> material = new ComboBox<String>();
        material.addItem("Tecido");
        material.addItem("Pele");
        material.addItem("Lona");
        material.addItem("Veludo");
        material.setPreferredSize(size);
        // ANO COLEÇÃO
        final Label anoColecaoLabel = new Label("Ano Coleção");
        final TextBox anoColecao = new TextBox(size).setValidationPattern(Pattern.compile("^[0-9]{0,4}?$"));

        // --- SAPATILHAS ---
        // TAMANHO
        final Label tamanhoLabel = new Label("Tamanho");
        final TextBox tamanho = new TextBox(size).setValidationPattern(Pattern.compile("^[0-9]{0,2}?$"));
        // ATACADORES
        final Label atacadoresLabel = new Label("Material");
        final ComboBox<String> atacadores = new ComboBox<String>();
        atacadores.addItem("Atacadores");
        atacadores.addItem("Atilhos");
        atacadores.setPreferredSize(size);
        // COR
        final Label corLabel = new Label("Cor");
        final TextBox cor = new TextBox(size);

        // --- TSHIRT ---
        // TAMANHO
        final Label tamanhoTShirtLabel = new Label("Tamanho");
        final ComboBox<String> tamanhoTShirt = new ComboBox<String>();
        tamanhoTShirt.addItem("S");
        tamanhoTShirt.addItem("M");
        tamanhoTShirt.addItem("L");
        tamanhoTShirt.addItem("XL");
        tamanhoTShirt.setPreferredSize(size);
        // PADRÃO
        final Label padraoLabel = new Label("Padrão");
        final ComboBox<String> padrao = new ComboBox<String>();
        padrao.addItem("Liso");
        padrao.addItem("Riscos");
        padrao.addItem("Palmeira");
        padrao.setPreferredSize(size);

        final Button confirmButton = new Button("Confirmar", new Runnable() {
            @Override
            public void run() {
                if (tipo.getSelectedItem().equals("Mala")) {
                    loja.criaArtigo(new String[] {
                            Integer.toString(Artigo.MALA),
                            codigoVendedor.getText(),
                            ManageUtils.parseEstadoUtilizacao(estadoUtilizacao.getText()),
                            numDonos.getText(),
                            descricao.getText(),
                            marca.getText(),
                            precoBase.getText(),
                            transportadora.getText(),
                            comprimento.getText(),
                            largura.getText(),
                            altura.getText(),
                            ManageUtils.parseMaterialMala(material.getText()),
                            anoColecao.getText()
                    });
                }
                if (tipo.getSelectedItem().equals("Sapatilhas")) {
                    loja.criaArtigo(new String[] {
                            Integer.toString(Artigo.SAPATILHAS),
                            codigoVendedor.getText(),
                            ManageUtils.parseEstadoUtilizacao(estadoUtilizacao.getText()),
                            numDonos.getText(),
                            descricao.getText(),
                            marca.getText(),
                            precoBase.getText(),
                            transportadora.getText(),
                            tamanho.getText(),
                            Integer.toString(atacadores.getSelectedIndex()),
                            cor.getText(),
                            anoColecao.getText()
                    });
                }
                if (tipo.getSelectedItem().equals("T-Shirt")) {
                    loja.criaArtigo(new String[] {
                            Integer.toString(Artigo.TSHIRT),
                            codigoVendedor.getText(),
                            ManageUtils.parseEstadoUtilizacao(estadoUtilizacao.getText()),
                            numDonos.getText(),
                            descricao.getText(),
                            marca.getText(),
                            precoBase.getText(),
                            transportadora.getText(),
                            tamanhoTShirt.getSelectedItem(),
                            Integer.toString(padrao.getSelectedIndex())
                    });
                }
                window.close();
            }
        });

        tipo.addListener(new Listener() {
            @Override
            public void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction) {
                panel.removeAllComponents();
                panel.addComponent(tipoLabel);
                panel.addComponent(tipo);
                panel.addComponent(marcaLabel);
                panel.addComponent(marca);
                panel.addComponent(descricaoLabel);
                panel.addComponent(descricao);
                panel.addComponent(numDonosLabel);
                panel.addComponent(numDonos);
                panel.addComponent(estadoUtilizacaoLabel);
                panel.addComponent(estadoUtilizacao);
                panel.addComponent(precoBaseLabel);
                panel.addComponent(precoBase);
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
                }
                if (selectedIndex == 1) {
                    tamanhoLabel.addTo(panel);
                    tamanho.addTo(panel);
                    atacadoresLabel.addTo(panel);
                    atacadores.addTo(panel);
                    corLabel.addTo(panel);
                    cor.addTo(panel);
                    anoColecaoLabel.addTo(panel);
                    anoColecao.addTo(panel);
                }
                if (selectedIndex == 2) {
                    tamanhoTShirtLabel.addTo(panel);
                    tamanhoTShirt.addTo(panel);
                    padraoLabel.addTo(panel);
                    padrao.addTo(panel);
                }
                panel.addComponent(codigoVendedorLabel);
                panel.addComponent(codigoVendedor);
                panel.addComponent(transportadoraLabel);
                panel.addComponent(transportadora);
                panel.addComponent(confirmButton);
            }
        });

        // Mala
        panel.addComponent(tipoLabel);
        panel.addComponent(tipo);
        panel.addComponent(marcaLabel);
        panel.addComponent(marca);
        panel.addComponent(descricaoLabel);
        panel.addComponent(descricao);
        panel.addComponent(numDonosLabel);
        panel.addComponent(numDonos);
        panel.addComponent(estadoUtilizacaoLabel);
        panel.addComponent(estadoUtilizacao);
        panel.addComponent(precoBaseLabel);
        panel.addComponent(precoBase);
        panel.addComponent(comprimentoLabel);
        panel.addComponent(comprimento);
        panel.addComponent(larguraLabel);
        panel.addComponent(largura);
        panel.addComponent(alturaLabel);
        panel.addComponent(altura);
        panel.addComponent(materialLabel);
        panel.addComponent(material);
        panel.addComponent(anoColecaoLabel);
        panel.addComponent(anoColecao);
        panel.addComponent(codigoVendedorLabel);
        panel.addComponent(codigoVendedor);
        panel.addComponent(transportadoraLabel);
        panel.addComponent(transportadora);
        panel.addComponent(confirmButton);

        window.setComponent(panel);
        gui.addWindowAndWait(window);

    }

    public static void criarUtilizador(MultiWindowTextGUI gui, Vintage loja) {

        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        new Label("Email").addTo(panel);
        final TextBox email = new TextBox().setPreferredSize(new TerminalSize(50, 1)).addTo(panel);

        new Label("Nome").addTo(panel);
        final TextBox nome = new TextBox().setPreferredSize(new TerminalSize(50, 1)).addTo(panel);

        new Label("Morada").addTo(panel);
        final TextBox morada = new TextBox().setPreferredSize(new TerminalSize(50, 1)).addTo(panel);

        new Label("NIF").addTo(panel);
        final TextBox nif = new TextBox().setValidationPattern(Pattern.compile("^[0-9]{0,9}?$"))
                .setPreferredSize(new TerminalSize(50, 1)).addTo(panel);

        Button confirmButton = new Button("Confirmar", new Runnable() {
            @Override
            public void run() {
                loja.criaUtilizador(new String[] {
                        email.getText(),
                        nome.getText(),
                        morada.getText(),
                        nif.getText()
                });
                window.close();
            }
        });
        confirmButton.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    public static void criarEncomenda(MultiWindowTextGUI gui, Vintage loja) {

        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        new Label("Código Comprador").addTo(panel);
        final TextBox codigoVendedor = new TextBox(new TerminalSize(20, 1))
                .setValidationPattern(Pattern.compile("[0-9]*")).addTo(panel);

        new Label("Tamanho").addTo(panel);
        final ComboBox<String> material = new ComboBox<String>();
        material.addItem("Grande");
        material.addItem("Médio");
        material.addItem("Pequeno");
        material.addTo(panel);

        Button confirmButton = new Button("Confirmar", new Runnable() {
            @Override
            public void run() {
                loja.criaEncomenda(new String[] {
                        codigoVendedor.getText(),
                        ManageUtils.parseTamanhoEncomenda(material.getSelectedItem())
                });
                window.close();
            }
        });
        confirmButton.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    public static void criarTransportadora(MultiWindowTextGUI gui, Vintage loja) {

        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        new Label("Nome").addTo(panel);
        final TextBox nome = new TextBox().setPreferredSize(new TerminalSize(35, 1)).addTo(panel);

        new Label("Margem Lucro").addTo(panel);
        final TextBox margemLucro = new TextBox().setPreferredSize(new TerminalSize(35, 1))
                .setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"))
                .addTo(panel);

        new Label("Margem Extra").addTo(panel);
        final TextBox margemExtra = new TextBox().setPreferredSize(new TerminalSize(35, 1))
                .setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"))
                .addTo(panel);

        Button confirmButton = new Button("Confirmar", new Runnable() {
            @Override
            public void run() {
                loja.criaTransportadora(new String[] {
                        nome.getText(),
                        margemLucro.getText(),
                        margemExtra.getText()
                });
                window.close();
            }
        });
        confirmButton.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

}
