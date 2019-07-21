package com.splendid.generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Consumer;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Artifact {

  public static void walk(Path source, Consumer<Path> action) throws IOException {
    Files.walk(source, Integer.MAX_VALUE)
        .forEach(action);
  }

  public static void copy(Path source, Path dest) {
    try {
      Files.copy(source, dest, REPLACE_EXISTING);
    } catch (IOException exception) {
      throw new RuntimeException(exception.getMessage(), exception);
    }
  }

  public static boolean hasExtension(Path path, String extension) {
    return Files.isRegularFile(path) && path.getFileName().toString().endsWith(extension);
  }

  public static boolean exists(Path path) {
    return Files.exists(path);
  }

  public static void createDirectory(Path path) throws IOException {
    Files.createDirectory(path);
  }

  public static void delete(Path path) throws IOException {
    if (Files.exists(path)) {
      Files.walk(path, Integer.MAX_VALUE)
        .sorted(Comparator.reverseOrder())
        .map(Path::toFile)
        .forEach(File::delete);
    }
  }
}
