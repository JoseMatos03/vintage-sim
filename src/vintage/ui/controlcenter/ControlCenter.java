package vintage.ui.controlcenter;

import java.util.Arrays;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;

import vintage.Vintage;
import vintage.ui.UI;
import vintage.ui.timetravel.TimeTravel;

public class ControlCenter {
    public static void menuControlo(MultiWindowTextGUI gui, BasicWindow window, Vintage loja) {
        Panel panel = new Panel();

        new Button("Time Travel...", new Runnable() {
            @Override
            public void run() {
                TimeTravel.menuTimeTravel(gui, loja);
            }
        }).addTo(panel);

        new Button("Wipeout...", new Runnable() {
            @Override
            public void run() {
                BasicWindow actionWindow = new BasicWindow();
                actionWindow.setHints(Arrays.asList(Window.Hint.CENTERED));
                actionWindow.setCloseWindowWithEscape(true);
                Panel actionPanel = new Panel();

                new Label("Toda a loja será apagada. Tens a certeza?").addTo(actionPanel);
                new Button("Confirmar", new Runnable() {
                    public void run() {
                        loja.wipeAll();
                        actionWindow.close();
                    }
                }).addTo(actionPanel);

                actionWindow.setComponent(actionPanel);
                gui.addWindowAndWait(actionWindow);
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
        // Botao wipe
    }
}
