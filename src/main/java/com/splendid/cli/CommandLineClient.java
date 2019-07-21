package com.splendid.cli;

import com.splendid.config.Configuration;
import com.splendid.generator.TemplateEngine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Path;

@Command(name = "splendid", mixinStandardHelpOptions = true, version = "Splendid generator 1.0")
public class CommandLineClient implements Runnable {

  @Option(
      names = { "-t", "--template" },
      description = "Project template. Path to folder that contains the template of the project.",
      required = true
  )
  private Path template = null;

  @Option(
      names = { "-o", "--output" },
      description = "Project output folder. Path to the project output."
  )
  private Path output = Configuration.DEFAULT_OUTPUT_PATH;

  @Option(
      names = { "-f", "--file" },
      description = "Project data. Path to the project JSON data."
  )
  private Path file = null;

  @Option(
      names = { "-d", "--data" },
      description = "Project data. Project JSON data."
  )
  private String data = null;

  @Override
  public void run() {
    try {
      Configuration config = getConfiguration();
      TemplateEngine.run(config);
    } catch (IOException exception) {
      throw new RuntimeException(exception.getMessage(), exception);
    }
  }

  private Configuration getConfiguration() {
    Configuration config = new Configuration();
    config
        .withTemplate(template)
        .withTarget(output);
    if (file != null) {
      config.withData(file);
    } else {
      config.withData(data);
    }
    return config;
  }
}
