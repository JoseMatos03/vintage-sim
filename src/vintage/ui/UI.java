package vintage.ui;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import vintage.Vintage;
import vintage.ui.info.Info;
import vintage.ui.manage.Manage;
import vintage.ui.stats.Stats;

public class UI {

    public static void menu(MultiWindowTextGUI gui, BasicWindow window, Vintage loja) {
        Panel panel = new Panel();

        Button infoButton = new Button("Informação", new Runnable() {
            @Override
            public void run() {
                Info.menuInformacao(gui, window, loja);
            }
        });
        infoButton.addTo(panel);

        Button manButton = new Button("Gerir", new Runnable() {
            @Override
            public void run() {
                Manage.menuManutencao(gui, window, loja);
            }
        });
        manButton.addTo(panel);

        Button statsButton = new Button("Estatísticas", new Runnable() {
            @Override
            public void run() {
                Stats.menuEstatisticas(gui, loja);
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

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

}
