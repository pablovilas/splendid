package com.splendid.generator;

import com.splendid.config.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TemplateEngine {

  public static void run(Configuration config) throws IOException {

    Path origin = config.getTemplate();
    Path target = config.getTarget();

    Artifact.walk(origin, originArtifact -> {
      Path targetArtifact = TemplateProcessor.generatePath(target.resolve(origin.relativize(originArtifact)), config);
      if (Artifact.isDirectory(originArtifact)) {
        Artifact.createDirectory(targetArtifact);
      } else if (ignoredArtifact(originArtifact)) {
        Artifact.copy(originArtifact, targetArtifact);
      } else {
        TemplateProcessor.generateArtifact(originArtifact, targetArtifact, config);
      }
    });
  }

  private static boolean ignoredArtifact(Path path) {
    //return Artifact.hasExtension(path, Configuration.DEFAULT_TEMPLATE_EXTENSION); // TODO: Template extension can by configured
    return !Files.isDirectory(path) && path.getFileName().toString().contains("DS");
  }

}
