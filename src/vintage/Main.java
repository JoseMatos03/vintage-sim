package vintage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import vintage.artigos.Artigo;
import vintage.artigos.mala.Mala;
import vintage.artigos.sapatilhas.Sapatilhas;
import vintage.artigos.tshirt.TShirt;
import vintage.ui.UI;

import static vintage.utils.SaveLoad.save;
import static vintage.utils.SaveLoad.load;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type,
                    JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                Instant instant = Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong());
                return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            }
        }).registerTypeAdapter(Artigo.class, new JsonDeserializer<Artigo>() {
            @Override
            public Artigo deserialize(JsonElement json, Type type,
                    JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                int tipo = json.getAsJsonObject().get("tipo").getAsInt();

                switch (tipo) {
                    case 0:
                        return jsonDeserializationContext.deserialize(json, Mala.class);
                    case 1:
                        return jsonDeserializationContext.deserialize(json, Sapatilhas.class);
                    case 2:
                        return jsonDeserializationContext.deserialize(json, TShirt.class);
                    default:
                        break;
                }
                return null;
            }
        }).setPrettyPrinting().create();

        Vintage loja = load(gson);

        // String[] utilizadorNovo = scanner.nextLine().split(",");
        // loja.criaUtilizador(utilizadorNovo);

        // String[] transporatodaNova = scanner.nextLine().split(",");
        // loja.criaTransportadora(transporatodaNova);

        // String[] artigoNovo = scanner.nextLine().split(",");
        // loja.criaArtigo(artigoNovo);

        // UI
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(),
                new EmptySpace(TextColor.ANSI.BLUE));
        BasicWindow window = new BasicWindow();

        screen.startScreen();

        UI.menu(gui, window, loja);
        // UI.listaUtilizadores(gui, loja.getUtilizadores());
        // UI.listaArtigos(gui, loja.getArtigos());

        scanner.close();
        screen.close();

        save(gson, loja);
    }
}
