package com.splendid.generator;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class TemplateProcessor {

  private static MustacheFactory mustacheFactory = new DefaultMustacheFactory();

  public static void generateArtifact(Path origin, Path target, Map<String, Object> data) {
    try {
      process(origin, target, data);
    } catch (IOException exception) {
      throw new RuntimeException(exception.getMessage(), exception);
    }
  }

  private static void process(Path origin, Path target, Map<String, Object> data) throws IOException {
    Reader reader = Files.newBufferedReader(origin);
    Writer writer = new FileWriter(target.toFile());

    Mustache mustache = mustacheFactory.compile(reader, origin.getFileName().toString());
    mustache.execute(writer, data);

    reader.close();
    writer.close();
  }

}
