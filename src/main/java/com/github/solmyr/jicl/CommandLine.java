package com.github.solmyr.jicl;

import java.io.IOException;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import com.github.solmyr.jicl.commands.manager.CommandsManager;
import com.github.solmyr.jicl.commands.manager.ICommand;
import com.github.solmyr.jicl.specialcommands.ExitCommand;

public class CommandLine {
	private LineReader reader;
	private CommandsManager commandsManager;
	
	private final String PROMPT_SYMBOL;
	private final String UNKNOWN_COMMAND_MESSAGE;
	
	public static CommandLineBuilder builder() {
		return new CommandLineBuilder();
	}

	public CommandLine(CommandLineConfig clc) {
		PROMPT_SYMBOL = clc.getPromptSymbol();
		UNKNOWN_COMMAND_MESSAGE = clc.getUnknownCommandMessage();
		
		try {
			initCommands(clc);
			initLine();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void initCommands(CommandLineConfig clc) {
		commandsManager = new CommandsManager(clc);
	}

	private void initLine() throws IOException {
		Terminal terminal = TerminalBuilder.terminal();
		reader = LineReaderBuilder.builder()
								  .completer(new StringsCompleter(commandsManager.getCommandNames()))
							      .terminal(terminal)
							      .build();
		
	}

	public void run() {
		String line = null;
		try {
			while (true) {
				try {
					line = reader.readLine(PROMPT_SYMBOL);
					ICommand command = commandsManager.getCommandInstance(line);
					if(command == null) {
						System.out.println(UNKNOWN_COMMAND_MESSAGE);
					} else {
						command.process();
					}
					if(command instanceof ExitCommand) {
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		} catch (UserInterruptException e) {
			
		} catch (EndOfFileException e) {
			return;
		}

	}
}
