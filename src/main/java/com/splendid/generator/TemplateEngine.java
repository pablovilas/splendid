package com.splendid.generator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class TemplateEngine {

  private static final String TEMPLATE_EXTENSION = ".txt";

  public static void run(Path origin, Path target, Map<String, Object> data) throws IOException {

    if (!Artifact.exists(origin)) {
      throw new IOException(String.format("Template not available in %s", origin.toString()));
    }

    if (data == null || data.isEmpty()) {
      throw new IllegalArgumentException("Data object cannot be null or empty");
    }

    if (!Artifact.exists(target)) {
      Artifact.createDirectory(target);
    }

    Artifact.walk(origin, originArtifact -> {

      Path targetArtifact = target.resolve(origin.relativize(originArtifact));

      if (isTemplateFile(originArtifact)) {
        TemplateProcessor.generateArtifact(originArtifact, targetArtifact, data);
      } else {
        Artifact.copy(originArtifact, targetArtifact);
      }

    });
  }

  private static boolean isTemplateFile(Path path) {
    return Artifact.hasExtension(path, TEMPLATE_EXTENSION); // TODO: Template extension can by configured
  }

}
