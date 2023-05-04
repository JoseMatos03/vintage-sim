package vintage;

import static vintage.utils.SaveLoad.load;
import static vintage.utils.SaveLoad.prepareGsonLoader;
import static vintage.utils.SaveLoad.save;

import java.io.IOException;
import java.util.Timer;

import com.google.gson.Gson;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import vintage.controlcenter.AutoRun;
import vintage.controlcenter.Clock;
import vintage.ui.UI;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = prepareGsonLoader(false);

        Vintage loja = load(gson);
        AutoRun runner = new AutoRun();
        Timer timer = new Timer();

        Clock.run(timer, loja);
        loja.entregarEncomendas();

        // UI
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(125, 25))
                .createTerminal();
        Screen screen = new TerminalScreen(terminal);
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(),
                new EmptySpace(TextColor.ANSI.BLUE));
        BasicWindow window = new BasicWindow();

        screen.startScreen();
        UI.menu(gui, window, loja, runner);
        
        screen.close();
        timer.cancel();

        save(gson, loja);
    }
}
