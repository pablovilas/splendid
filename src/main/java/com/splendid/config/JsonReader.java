package com.splendid.config;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class JsonReader {

  private static Gson gson = new Gson();

  public static Map fromFile(Path path) throws IOException {
    Reader reader = Files.newBufferedReader(path);
    Map object = gson.fromJson(reader, Map.class);
    reader.close();
    return object;
  }

  public static Map fromString(String json) {
    return gson.fromJson(json, Map.class);
  }
}
