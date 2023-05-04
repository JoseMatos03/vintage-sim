package vintage.ui.controlcenter;

import static vintage.ui.ErrorHandler.handleError;

import java.util.Arrays;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;

import vintage.Vintage;
import vintage.controlcenter.AutoRun;
import vintage.ui.UI;
import vintage.ui.timetravel.TimeTravel;
import vintage.utils.ErrorCode;

public class ControlCenter {
    public static void menuControlo(MultiWindowTextGUI gui, BasicWindow window, Vintage loja, AutoRun runner) {
        Panel panel = new Panel();

        new Button("Auto Run...", new Runnable() {
            @Override
            public void run() {
                ErrorCode error = runner.readAndExecute(loja);
                handleError(gui, error);
            }
        }).addTo(panel);

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
                UI.menu(gui, window, loja, runner);
            }
        }).addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
        // Botao wipe
    }
}
