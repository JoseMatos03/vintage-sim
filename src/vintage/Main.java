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
        }).create();

        Vintage loja = load(gson);

        // String[] utilizadorNovo = scanner.nextLine().split(",");
        // loja.criaUtilizador(utilizadorNovo);

        // String[] transporatodaNova = scanner.nextLine().split(" ");
        // loja.criaTransportadora(transporatodaNova);

        // String[] artigoNovo = scanner.nextLine().split(" ");
        // loja.criaArtigo(artigoNovo);

        UI.listaUtilizadores(loja, loja.getUtilizadores());

        // save(gson, loja);
        scanner.close();
    }
}
