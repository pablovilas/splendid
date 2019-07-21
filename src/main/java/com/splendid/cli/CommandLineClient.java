package com.splendid.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.nio.file.Path;

@Command(
    name = "splendid",
    mixinStandardHelpOptions = true,
    version = "Splendid generator 1.0"
)
public class CommandLineClient implements Runnable {

  @Option(
      names = { "-v", "--verbose" },
      description = "Verbose mode. Helpful for troubleshooting."
  )
  private boolean verbose = false;

  @Option(
      names = { "-t", "--template" },
      description = "Project template. Path to folder that contains the template of the project.",
      required = true
  )
  private Path template = null;

  @Option(
      names = { "-e", "--extension" },
      description = "Template file extension. Specifies the template files extension type. Default is \".tpl\"."
  )
  private String extension = null;

  @Option(
      names = { "-d", "--data" },
      description = "Project data. Path to the project JSON data.",
      required = true
  )
  private Path data = null;

  @Override
  public void run() {
    System.out.println("verbose: " + verbose);
    System.out.println("template: " + template);
    System.out.println("extension: " + extension);
    System.out.println("data: " + data);
  }
}
