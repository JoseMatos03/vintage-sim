package vintage.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import vintage.Vintage;
import vintage.artigos.Artigo;
import vintage.artigos.mala.Mala;
import vintage.artigos.sapatilhas.Sapatilhas;
import vintage.artigos.tshirt.TShirt;

public class SaveLoad {

    public static final String FILEPATH = System.getProperty("user.dir") + File.separator + "out" + File.separator
            + "save.json";

    public static Gson prepareGsonLoader(boolean usePrettyPrinting) {
        GsonBuilder gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .registerTypeAdapter(Artigo.class, new JsonDeserializer<Artigo>() {
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
                });

        if (usePrettyPrinting)
            gson.setPrettyPrinting();

        return gson.create();
    }

    public static void save(Gson gson, Vintage loja) {
        try {
            Writer writer = new FileWriter(FILEPATH);
            gson.toJson(loja, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Vintage load(Gson gson) {
        try (Reader reader = new FileReader(FILEPATH)) {
            BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
            if (br.readLine() == null) {
                br.close();
                return new Vintage();
            }

            br.close();
            return new Vintage(gson.fromJson(reader, Vintage.class));
        } catch (Exception e) {
            return new Vintage();
        }
    }

}
