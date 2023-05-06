package vintage.ui.info;

import static vintage.ui.ErrorHandler.handleError;
import static vintage.utils.vintage.Utils.getArtigo;
import static vintage.utils.vintage.Utils.getEncomenda;
import static vintage.utils.vintage.Utils.getTransportadora;
import static vintage.utils.vintage.Utils.getUtilizador;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.table.Table;

import vintage.Vintage;
import vintage.artigos.Artigo;
import vintage.controlcenter.AutoRun;
import vintage.encomendas.Encomenda;
import vintage.transportadoras.Transportadora;
import vintage.ui.UI;
import vintage.utilizadores.Utilizador;
import vintage.utils.ErrorCode;
import vintage.utils.artigos.Utils;
import vintage.utils.ui.InfoUtils;

public class Info {

    public static void menuInformacao(MultiWindowTextGUI gui, BasicWindow window, Vintage loja, AutoRun runner) {

        Panel panel = new Panel();

        Button artigosButton = new Button("Ver artigos", new Runnable() {
            @Override
            public void run() {
                listaArtigos(gui, loja, loja.getArtigos());
            }
        });
        artigosButton.addTo(panel);

        Button utilizadoresButton = new Button("Ver utilizadores", new Runnable() {
            @Override
            public void run() {
                listaUtilizadores(gui, loja, loja.getUtilizadores());
            }
        });
        utilizadoresButton.addTo(panel);

        Button encomendasButton = new Button("Ver encomendas", new Runnable() {
            @Override
            public void run() {
                listaEncomendas(gui, loja, loja.getEncomendas());
            }
        });
        encomendasButton.addTo(panel);

        Button transportadorasButton = new Button("Ver transportadoras", new Runnable() {
            @Override
            public void run() {
                listaTransportadoras(gui, loja, loja.getTransportadoras());
            }
        });
        transportadorasButton.addTo(panel);

        Button goBackButton = new Button("Voltar", new Runnable() {
            @Override
            public void run() {
                UI.menu(gui, window, loja, runner);
            }
        });
        goBackButton.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    public static void listaArtigos(MultiWindowTextGUI gui, Vintage loja, List<Artigo> artigos) {

        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();

        Table<String> table = new Table<String>("Código", "Tipo", "Marca", "Preço", "Nº Donos", "Estado", "Premium");
        for (Artigo artigo : artigos) {
            String codigo = Integer.toString(artigo.getCodigo());
            String tipo = InfoUtils.parseTipoArtigo(artigo.getTipo());
            String marca = artigo.getMarca();
            String preco = Float.toString(Utils.arrondarCentesimas(artigo.calcularPreco()));
            String numDonos = Integer.toString(artigo.getNumDonos());
            String estadoDeUtilizacao = Float.toString(Utils.arrondarCentesimas(artigo.getEstadoUtilizacao()));
            String premium = InfoUtils.parsePremium(artigo.getPremiumEstado());
            table.getTableModel().addRow(codigo, tipo, marca, preco, numDonos, estadoDeUtilizacao, premium);
        }
        if (table.getTableModel().getRowCount() > 0) {
            table.setSelectAction(new Runnable() {
                // Opções para cada item
                @Override
                public void run() {
                    BasicWindow actionWindow = new BasicWindow();
                    actionWindow.setHints(Arrays.asList(Window.Hint.CENTERED));
                    actionWindow.setCloseWindowWithEscape(true);

                    ActionListBox actionListBox = new ActionListBox();
                    // Mais informação sobre o artigo
                    actionListBox.addItem("Mais informação...", new Runnable() {
                        @Override
                        public void run() {
                            Panel actionPanel = new Panel();

                            String codigo = table.getTableModel().getRow(table.getSelectedRow()).get(0);
                            Artigo artigo = getArtigo(artigos, Integer.parseInt(codigo));

                            new Label(artigo.toString()).setPreferredSize(new TerminalSize(70, 18)).addTo(actionPanel);

                            actionWindow.setComponent(actionPanel);
                        }
                    });
                    // Remover artigo
                    actionListBox.addItem("Remover...", new Runnable() {
                        @Override
                        public void run() {
                            String codigo = table.getTableModel().getRow(table.getSelectedRow()).get(0);
                            ErrorCode erro = loja.removeArtigo(codigo);
                            handleError(gui, erro);
                            actionWindow.close();
                            window.close();
                        }
                    });

                    actionWindow.setComponent(actionListBox);
                    gui.addWindowAndWait(actionWindow);
                }
            });
        }
        table.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    public static void listaUtilizadores(MultiWindowTextGUI gui, Vintage loja, List<Utilizador> utilizadores) {

        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();

        Table<String> table = new Table<String>("Código", "Nome", "Email", "Morada", "NIF", "Vendas");
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getAtividade() == Utilizador.INATIVA)
                continue;

            String codigo = Integer.toString(utilizador.getCodigo());
            String nome = utilizador.getNome();
            String email = utilizador.getEmail();
            String morada = utilizador.getMorada();
            String nif = Integer.toString(utilizador.getNumeroFiscal());
            String vendas = Float.toString(Utils.arrondarCentesimas(utilizador.getValorEmVendas()));
            table.getTableModel().addRow(codigo, nome, email, morada, nif, vendas);
        }
        if (table.getTableModel().getRowCount() > 0) {
            table.setSelectAction(new Runnable() {
                // Opções para cada item
                @Override
                public void run() {
                    BasicWindow actionWindow = new BasicWindow();
                    actionWindow.setHints(Arrays.asList(Window.Hint.CENTERED));
                    actionWindow.setCloseWindowWithEscape(true);

                    ActionListBox actionListBox = new ActionListBox();
                    // Mais informação
                    actionListBox.addItem("Mais informação...", new Runnable() {
                        @Override
                        public void run() {
                            Panel actionPanel = new Panel();

                            String codigo = table.getTableModel().getRow(table.getSelectedRow()).get(0);
                            Utilizador utilizador = getUtilizador(utilizadores, Integer.parseInt(codigo));

                            new Label(utilizador.toString()).setPreferredSize(new TerminalSize(70, 12))
                                    .addTo(actionPanel);

                            actionWindow.setComponent(actionPanel);
                        }
                    });
                    // Apagar utilizador
                    actionListBox.addItem("Apagar...", new Runnable() {
                        @Override
                        public void run() {
                            Panel actionPanel = new Panel();

                            new Label("Todos os artigos associados serão apagados. Continuar?").addTo(actionPanel);
                            new Button("Confirmar", new Runnable() {
                                public void run() {
                                    String codigo = table.getTableModel().getRow(table.getSelectedRow()).get(0);
                                    loja.apagaUtilizador(codigo);
                                    actionWindow.close();
                                    window.close();
                                }
                            }).addTo(actionPanel);
                            actionWindow.setComponent(actionPanel);
                        }
                    });

                    actionWindow.setComponent(actionListBox);
                    gui.addWindowAndWait(actionWindow);
                }
            });
        }
        table.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    public static void listaEncomendas(MultiWindowTextGUI gui, Vintage loja, List<Encomenda> encomendas) {

        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();

        Table<String> table = new Table<String>("Código", "Dimensão", "Estado", "Preço", "Data Criação");
        for (Encomenda encomenda : encomendas) {
            String codigo = Integer.toString(encomenda.getCodigo());
            String dimensaoEncomenda = InfoUtils.parseDimensao(encomenda.getDimensaoEncomenda());
            String estadoEncomenda = InfoUtils.parseEstadoEncomenda(encomenda.getEstadoEncomenda());
            String precoEncomenda = Float.toString(Utils.arrondarCentesimas(encomenda.getPrecoEncomenda()));
            String dataCriacao = encomenda.getDataCriacao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            table.getTableModel().addRow(codigo, dimensaoEncomenda, estadoEncomenda, precoEncomenda, dataCriacao);
        }
        if (table.getTableModel().getRowCount() > 0) {
            table.setSelectAction(new Runnable() {
                // Opções para cada item
                @Override
                public void run() {
                    BasicWindow actionWindow = new BasicWindow();
                    actionWindow.setHints(Arrays.asList(Window.Hint.CENTERED));
                    actionWindow.setCloseWindowWithEscape(true);

                    ActionListBox actionListBox = new ActionListBox();
                    // Mais informação
                    actionListBox.addItem("Mais informação...", new Runnable() {
                        @Override
                        public void run() {
                            Panel actionPanel = new Panel();

                            String codigo = table.getTableModel().getRow(table.getSelectedRow()).get(0);
                            Encomenda encomenda = getEncomenda(encomendas, Integer.parseInt(codigo));

                            new Label(encomenda.toString()).setPreferredSize(new TerminalSize(70, 10))
                                    .addTo(actionPanel);

                            actionWindow.setComponent(actionPanel);
                        }
                    });
                    // Adicionar artigo
                    actionListBox.addItem("Adicionar artigo...", new Runnable() {
                        @Override
                        public void run() {
                            Panel actionPanel = new Panel(new GridLayout(2));

                            String codigo = table.getTableModel().getRow(table.getSelectedRow()).get(0);
                            new Label("Código Artigo").addTo(actionPanel);
                            final TextBox codigoArtigo = new TextBox().setValidationPattern(Pattern.compile("[0-9]*"))
                                    .addTo(actionPanel);

                            new Button("Confirmar", new Runnable() {
                                @Override
                                public void run() {
                                    ErrorCode error = loja.adicionarArtigoEmEncomenda(codigo, codigoArtigo.getText());

                                    handleError(gui, error);
                                    if (error.equals(ErrorCode.NO_ERRORS)) {
                                        actionWindow.close();
                                        window.close();
                                    }
                                }
                            }).addTo(actionPanel);

                            actionWindow.setComponent(actionPanel);
                        }
                    });
                    // Remover artigo
                    actionListBox.addItem("Remover artigo...", new Runnable() {
                        @Override
                        public void run() {
                            Panel actionPanel = new Panel(new GridLayout(2));

                            String codigo = table.getTableModel().getRow(table.getSelectedRow()).get(0);
                            new Label("Código Artigo").addTo(actionPanel);
                            final TextBox codigoArtigo = new TextBox().setValidationPattern(Pattern.compile("[0-9]*"))
                                    .addTo(actionPanel);

                            new Button("Confirmar", new Runnable() {
                                @Override
                                public void run() {
                                    ErrorCode error = loja.removerArtigoEmEncomenda(codigo, codigoArtigo.getText());

                                    handleError(gui, error);
                                    if (error.equals(ErrorCode.NO_ERRORS)) {
                                        actionWindow.close();
                                        window.close();
                                    }
                                }
                            }).addTo(actionPanel);

                            actionWindow.setComponent(actionPanel);
                        }
                    });
                    // Expedir
                    actionListBox.addItem("Expedir...", new Runnable() {
                        @Override
                        public void run() {
                            String codigo = table.getTableModel().getRow(table.getSelectedRow()).get(0);
                            ErrorCode error = loja.expedirEncomenda(codigo);
                            handleError(gui, error);
                            if (error.equals(ErrorCode.NO_ERRORS)) {
                                actionWindow.close();
                                window.close();
                            }
                        }
                    });
                    // Cancelar encomenda
                    actionListBox.addItem("Cancelar...", new Runnable() {
                        @Override
                        public void run() {
                            String codigo = table.getTableModel().getRow(table.getSelectedRow()).get(0);
                            ErrorCode error = loja.cancelaEncomenda(codigo);
                            handleError(gui, error);
                            if (error.equals(ErrorCode.NO_ERRORS)) {
                                actionWindow.close();
                                window.close();
                            }
                        }
                    });

                    actionWindow.setComponent(actionListBox);
                    gui.addWindowAndWait(actionWindow);
                }
            });
        }
        table.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    public static void listaTransportadoras(MultiWindowTextGUI gui, Vintage loja,
            List<Transportadora> transportadoras) {

        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();

        Table<String> table = new Table<String>("Nome", "Margem Lucro", "Margem Extra", "Valor de Expedição", "Lucro",
                "Premium");
        for (Transportadora transportadora : transportadoras) {
            String nome = transportadora.getNome();
            String margemLucro = Float.toString(Utils.arrondarCentesimas(transportadora.getMargemLucro()));
            String margemExtra = Float.toString(Utils.arrondarCentesimas(transportadora.getMargemExtra()));
            String valorExpedicao = Float.toString(Utils.arrondarCentesimas(transportadora.getValorExpedicao()));
            String lucro = Float.toString(Utils.arrondarCentesimas(transportadora.getLucro()));
            String premium = InfoUtils.parsePremium(transportadora.getPremiumEstado());
            table.getTableModel().addRow(nome, margemLucro, margemExtra, valorExpedicao, lucro, premium);
        }
        if (table.getTableModel().getRowCount() > 0) {
            table.setSelectAction(new Runnable() {
                // Opções para cada item
                @Override
                public void run() {
                    BasicWindow actionWindow = new BasicWindow();
                    actionWindow.setHints(Arrays.asList(Window.Hint.CENTERED));
                    actionWindow.setCloseWindowWithEscape(true);

                    ActionListBox actionListBox = new ActionListBox();
                    // Mais informação
                    actionListBox.addItem("Mais informação...", new Runnable() {
                        @Override
                        public void run() {
                            Panel actionPanel = new Panel();

                            String nome = table.getTableModel().getRow(table.getSelectedRow()).get(0);
                            Transportadora transportadora = getTransportadora(transportadoras, nome);

                            new Label(transportadora.toString()).setPreferredSize(new TerminalSize(70, 10))
                                    .addTo(actionPanel);

                            actionWindow.setComponent(actionPanel);
                        }
                    });
                    // Editar valor de cálculo
                    actionListBox.addItem("Editar...", new Runnable() {
                        @Override
                        public void run() {
                            Panel actionPanel = new Panel(new GridLayout(2));

                            String nome = table.getTableModel().getRow(table.getSelectedRow()).get(0);
                            Transportadora transportadora = getTransportadora(transportadoras, nome);

                            if (!transportadora.getPremiumEstado()) {
                                handleError(gui, ErrorCode.PREMIUM_REQUIRED);
                                return;
                            }

                            new Label("Margem Lucro").addTo(actionPanel);
                            final TextBox margemLucro = new TextBox()
                                    .setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"))
                                    .addTo(actionPanel);

                            new Label("Margem Extra").addTo(actionPanel);
                            final TextBox margemExtra = new TextBox()
                                    .setValidationPattern(Pattern.compile("^[0-9]+(?:[.][0-9]{0,2})?$"))
                                    .addTo(actionPanel);

                            new Button("Confirmar", new Runnable() {
                                @Override
                                public void run() {
                                    ErrorCode error = transportadora.atualizarValores(margemLucro.getText(),
                                            margemExtra.getText());

                                    handleError(gui, error);
                                    if (error.equals(ErrorCode.NO_ERRORS)) {
                                        actionWindow.close();
                                        window.close();
                                    }
                                }
                            }).addTo(actionPanel);

                            actionWindow.setComponent(actionPanel);
                        }
                    });
                    // Apagar transportadora
                    actionListBox.addItem("Apagar...", new Runnable() {
                        @Override
                        public void run() {
                            String nome = table.getTableModel().getRow(table.getSelectedRow()).get(0);
                            loja.apagaTransportadora(nome);
                            actionWindow.close();
                            window.close();
                        }
                    });

                    actionWindow.setComponent(actionListBox);
                    gui.addWindowAndWait(actionWindow);
                }
            });
        }
        table.addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }
}
