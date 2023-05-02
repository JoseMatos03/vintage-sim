package vintage.ui;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;

import vintage.AutoRun;
import vintage.Vintage;
import vintage.ui.info.Info;
import vintage.ui.manage.Manage;
import vintage.ui.stats.Stats;
import vintage.ui.timetravel.TimeTravel;

public class UI {

    public static void menu(MultiWindowTextGUI gui, BasicWindow window, Vintage loja, AutoRun runner) {

        Panel panel = new Panel();

        Button infoButton = new Button("Informação", new Runnable() {
            @Override
            public void run() {
                Info.menuInformacao(gui, window, loja, runner);
            }
        });
        infoButton.addTo(panel);

        Button manButton = new Button("Gerir", new Runnable() {
            @Override
            public void run() {
                Manage.menuManutencao(gui, window, loja, runner);
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

        Button timeTravelButton = new Button("Time Travel...", new Runnable() {
            @Override
            public void run() {
                TimeTravel.menuTimeTravel(gui, loja);
            }
        });
        timeTravelButton.addTo(panel);

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
