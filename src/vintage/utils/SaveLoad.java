package vintage.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.google.gson.Gson;

import vintage.Vintage;

public class SaveLoad {

    public static final String FILEPATH = "out/save.json";

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
            return gson.fromJson(reader, Vintage.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Vintage();
    }

}
