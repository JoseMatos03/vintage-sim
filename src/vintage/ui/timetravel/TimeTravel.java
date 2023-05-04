package vintage.ui.timetravel;

import static vintage.ui.ErrorHandler.handleError;

import java.util.Arrays;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;

import vintage.Vintage;
import vintage.utils.ErrorCode;

public class TimeTravel {

    public static void menuTimeTravel(MultiWindowTextGUI gui, Vintage loja) {
        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        new Label("Data").addTo(panel);
        final TextBox data = new TextBox(new TerminalSize(20, 1)).addTo(panel);

        new Label("Hora").addTo(panel);
        final TextBox hora = new TextBox(new TerminalSize(20, 1)).addTo(panel);

        new Button("Confirmar", new Runnable() {
            @Override
            public void run() {
                ErrorCode error = loja.timeTravel(data.getText() + " " + hora.getText());
                handleError(gui, error);
                if (error.equals(ErrorCode.NO_ERRORS))
                    window.close();
            }
        }).addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

}
