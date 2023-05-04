package vintage.ui;

import static vintage.utils.vintage.Utils.FORMATTER;

import java.util.Timer;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;

import vintage.Vintage;
import vintage.controlcenter.AutoRun;
import vintage.controlcenter.Clock;
import vintage.ui.controlcenter.ControlCenter;
import vintage.ui.info.Info;
import vintage.ui.manage.Manage;
import vintage.ui.stats.Stats;

public class UI {

    public static void menu(MultiWindowTextGUI gui, BasicWindow window, Vintage loja, AutoRun runner) {

        Panel panel = new Panel();
        Timer timer = new Timer();

        new Label("Data:").addTo(panel);
        Label data = new Label(loja.getTempoAtual().format(FORMATTER)).setPreferredSize(new TerminalSize(17, 3));
        data.addTo(panel);

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

        Button controlCenterButton = new Button("Controlo", new Runnable() {
            @Override
            public void run() {
                ControlCenter.menuControlo(gui, window, loja, runner);
            }
        });
        controlCenterButton.addTo(panel);

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

        Clock.update(timer, loja, data);
        window.setComponent(panel);
        gui.addWindowAndWait(window);
        timer.cancel();

    }

}
