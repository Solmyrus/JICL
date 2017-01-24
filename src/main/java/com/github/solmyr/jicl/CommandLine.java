package com.github.solmyr.jicl;

import com.github.solmyr.jicl.commands.manager.CommandManager;
import com.github.solmyr.jicl.commands.manager.ICommand;
import com.github.solmyr.jicl.specialcommands.ExitCommand;

import java.io.IOException;
import java.io.PrintStream;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class CommandLine {
	private LineReader reader;
	private CommandManager commandManager;

	private final PrintStream outputStream;

	public static CommandLineBuilder builder() {
		return new CommandLineBuilder();
	}

	public CommandLine(CommandLineConfig clc) {
		outputStream = clc.getOutputStream();
		
		Strings.init(clc);
		
		try {
			initCommands(clc);
			initLine();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void initCommands(CommandLineConfig clc) {
		commandManager = new CommandManager(clc);
	}

	private void initLine() throws IOException {
		Terminal terminal = TerminalBuilder.terminal();
		reader = LineReaderBuilder.builder().completer(new StringsCompleter(commandManager.getCommandNames()))
				.terminal(terminal).build();

	}

	public void run() {	
		try {
			String line = null;
			boolean continueLoop = true;
			do {
				try {
					line = reader.readLine(Strings.m(StringsKey.PROMPT_SYMBOL));
					continueLoop = doCommand(line, outputStream);

				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} while (continueLoop);
		} catch (UserInterruptException e) {

		} catch (EndOfFileException e) {
	
		}
		
		outputStream.close();

	}

	public boolean doCommand(String line, PrintStream outputStream) {
		ICommand command = commandManager.getCommandInstance(line);
		if (command == null) {
			outputStream.println(Strings.m(StringsKey.UNKNOWN_COMMAND_MESSAGE));
		} else {
			doCommand(command, outputStream);
		}
		if (command instanceof ExitCommand) {
			return false;
		}
		return true;
	}
	
	public void doCommand(ICommand command, PrintStream outputStream) {
		command.process();
	}
	
	
}
