package vintage;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import vintage.ui.UI;
import static vintage.utils.SaveLoad.save;
import static vintage.utils.SaveLoad.load;
import static vintage.utils.SaveLoad.prepareGsonLoader;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Gson gson = prepareGsonLoader(true);

        Vintage loja = load(gson);
        loja.entregarEncomendas();

        // UI
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(),
                new EmptySpace(TextColor.ANSI.BLUE));
        BasicWindow window = new BasicWindow();

        screen.startScreen();
        UI.menu(gui, window, loja);

        scanner.close();
        screen.close();
        save(gson, loja);
    }
}
