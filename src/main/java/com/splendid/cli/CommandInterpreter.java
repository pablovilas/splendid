package com.splendid.cli;

import picocli.CommandLine;

public class CommandInterpreter {
  public static void init(String[] args) {
    int exitCode = new CommandLine(new CommandLineClient()).execute(args);
    System.exit(exitCode);
  }
}
