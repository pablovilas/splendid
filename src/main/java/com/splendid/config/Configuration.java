package com.splendid.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Configuration {

  public static final Path DEFAULT_OUTPUT_PATH = Paths.get("./out");

  private Path template;
  private Path target;
  private Map data;

  public Configuration() {
    super();
  }

  public Configuration withTemplate(Path template) {
    if (!Files.exists(template)) {
      throw new IllegalArgumentException(String.format("Path %s is not a valid template path.", template.toString()));
    }

    if (!Files.isDirectory(template)) {
      throw new IllegalArgumentException(String.format("Path %s is not a directory.", template.toString()));
    }

    if (!Files.isReadable(template)) {
      throw new IllegalArgumentException(String.format("Path %s is not readable.", template.toString()));
    }

    this.template = template;
    return this;
  }

  public Configuration withTarget(Path target) {
    this.target = target;
    return this;
  }

  public Configuration withData(String data) {
    this.withData(JsonReader.fromString(data));
    return this;
  }

  public Configuration withData(Map data) {
    if (data.isEmpty()) {
      throw new IllegalArgumentException("Data cannot be null or empty");
    }

    this.data = data;
    return this;
  }

  public Configuration withData(Path dataPath) {

    if (!Files.exists(dataPath)) {
      throw new IllegalArgumentException(String.format("Path %s is not a valid data path.", dataPath.toString()));
    }

    if (Files.isDirectory(dataPath)) {
      throw new IllegalArgumentException(String.format("Path %s is not a JSON file.", dataPath.toString()));
    }

    if (!Files.isReadable(dataPath)) {
      throw new IllegalArgumentException(String.format("Path %s is not readable.", dataPath.toString()));
    }

    try {
      this.withData(JsonReader.fromFile(dataPath));
      return this;
    } catch (IOException exception) {
      throw new IllegalArgumentException(String.format("Cannot read data from path %s.", dataPath.toString()), exception);
    }
  }

  public Path getTemplate() {
    return template;
  }

  public Path getTarget() {
    return target;
  }

  public Map getData() {
    return data;
  }

}
