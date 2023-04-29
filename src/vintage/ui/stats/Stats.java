package vintage.ui.stats;

import java.util.Arrays;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import vintage.Vintage;

public class Stats {

    public static void menuEstatisticas(MultiWindowTextGUI gui, Vintage loja) {

        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setCloseWindowWithEscape(true);

        Panel panel = new Panel();

        new Label(loja.toString()).setPreferredSize(new TerminalSize(75, 20)).addTo(panel);

        window.setComponent(panel);
        gui.addWindowAndWait(window);

    }

}
