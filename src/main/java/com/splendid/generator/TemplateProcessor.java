package com.splendid.generator;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.splendid.config.Configuration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TemplateProcessor {

  private static MustacheFactory mustacheFactory = new DefaultMustacheFactory();

  public static void generateArtifact(Path origin, Path target, Configuration config) {
    try {
      processArtifact(origin, target, config);
    } catch (IOException exception) {
      throw new RuntimeException(exception.getMessage(), exception);
    }
  }

  public static Path generatePath(Path target, Configuration config) {
    try {
      return processPath(target, config);
    } catch (IOException exception) {
      throw new RuntimeException(exception.getMessage(), exception);
    }
  }

  private static Path processPath(Path target, Configuration config) throws IOException {
    Reader reader = new StringReader(target.toString());
    Writer writer = new StringWriter();

    Mustache mustache = mustacheFactory.compile(reader, target.toString());
    mustache.execute(writer, config.getData());

    reader.close();
    writer.close();

    return Paths.get(writer.toString());
  }

  private static void processArtifact(Path origin, Path target, Configuration config) throws IOException {
    Reader reader = Files.newBufferedReader(origin);
    Writer writer = new FileWriter(target.toFile());

    Mustache mustache = mustacheFactory.compile(reader, origin.getFileName().toString());
    mustache.execute(writer, config.getData());

    reader.close();
    writer.close();
  }

}
