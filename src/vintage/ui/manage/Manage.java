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

public class Manage {

    public static void menuManutencao(MultiWindowTextGUI gui, BasicWindow window, Vintage loja) {
        Panel panel = new Panel();

        Button artigosButton = new Button("Criar artigo", new Runnable() {
            @Override
            public void run() {
                criarArtigo(gui, loja);
            }
        });
        artigosButton.addTo(panel);

        Button utilizadoresButton = new Button("Criar utilizadores", new Runnable() {
            @Override
            public void run() {
                criarUtilizador(gui, loja);
            }
        });
        utilizadoresButton.addTo(panel);

        // TODO Criar encomenda

        Button transportadorasButton = new Button("Criar transportadoras", new Runnable() {
            @Override
            public void run() {

            }
        });
        transportadorasButton.addTo(panel);

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

    public static void criarArtigo(MultiWindowTextGUI gui, Vintage loja) {

        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        // TIPO
        final Label tipoLabel = new Label("Tipo").addTo(panel);
        final ComboBox<String> tipo = new ComboBox<String>();
        tipo.addItem("Mala");
        tipo.addItem("Sapatilhas");
        tipo.addItem("T-Shirt");
        tipo.setPreferredSize(new TerminalSize(20, 1));
        panel.addComponent(tipo);

        // CÓDIGO VENDEDOR
        final Label codigoVendedorLabel = new Label("Código Vendedor").addTo(panel);
        final TextBox codigoVendedor = new TextBox(new TerminalSize(20, 1))
                .setValidationPattern(Pattern.compile("[0-9]*")).addTo(panel);

        // ESTADO
        final Label estadoUtilizacaoLabel = new Label("Estado").addTo(panel);
        final ComboBox<String> estadoUtilizacao = new ComboBox<String>();
        estadoUtilizacao.addItem("Sem uso");
        estadoUtilizacao.addItem("Pouco uso");
        estadoUtilizacao.addItem("Muito uso");
        estadoUtilizacao.addItem("Estragado");
        panel.addComponent(estadoUtilizacao);

        // Nº DONOS
        final Label numDonosLabel = new Label("Nº Donos").addTo(panel);
        final TextBox numDonos = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(panel);

        // DESCRIÇÃO
        final Label descricaoLabel = new Label("Descrição").addTo(panel);
        final TextBox descricao = new TextBox().addTo(panel);

        // MARCA
        final Label marcaLabel = new Label("Marca").addTo(panel);
        final TextBox marca = new TextBox().addTo(panel);

        // PREÇO BASE
        final Label precoBaseLabel = new Label("Preço Base").addTo(panel);
        final TextBox precoBase = new TextBox().setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"))
                .addTo(panel);

        // TRANSPORTADORA
        final Label transportadoraLabel = new Label("Transportadora").addTo(panel);
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
        // COR
        final Label corLabel = new Label("Cor");
        final TextBox cor = new TextBox();

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
                // TODO criar os outros tipos
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
                } else if (tipo.getSelectedItem().equals("Sapatilhas")) {
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
                } else if (tipo.getSelectedItem().equals("T-Shirt")) {
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
            }
        });
        confirmButton.addTo(panel);

        tipo.addListener(new Listener() {
            @Override
            public void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction) {
                panel.removeAllComponents();
                panel.addComponent(tipoLabel);
                panel.addComponent(tipo);
                panel.addComponent(codigoVendedorLabel);
                panel.addComponent(codigoVendedor);
                panel.addComponent(estadoUtilizacaoLabel);
                panel.addComponent(estadoUtilizacao);
                panel.addComponent(numDonosLabel);
                panel.addComponent(numDonos);
                panel.addComponent(descricaoLabel);
                panel.addComponent(descricao);
                panel.addComponent(marcaLabel);
                panel.addComponent(marca);
                panel.addComponent(precoBaseLabel);
                panel.addComponent(precoBase);
                panel.addComponent(transportadoraLabel);
                panel.addComponent(transportadora);
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
                    corLabel.addTo(panel);
                    cor.addTo(panel);
                    anoColecaoLabel.addTo(panel);
                    anoColecao.addTo(panel);
                } else if (selectedIndex == 2) {
                    tamanhoTShirtLabel.addTo(panel);
                    tamanhoTShirt.addTo(panel);
                    padraoLabel.addTo(panel);
                    padrao.addTo(panel);
                }
                panel.addComponent(confirmButton);
            }
        });

        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);
        window.setComponent(panel);

        gui.addWindowAndWait(window);

    }

    public static void criarUtilizador(MultiWindowTextGUI gui, Vintage loja) {

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
            }
        });
        confirmButton.addTo(panel);

        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);
        window.setComponent(panel);

        gui.addWindowAndWait(window);
    }

}
