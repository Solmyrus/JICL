package com.github.solmyr.jicl.commands.manager;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Set;
import java.util.Map;

import com.beust.jcommander.JCommander;
import com.github.solmyr.jicl.CommandLineConfig;
import com.github.solmyr.jicl.Strings;
import com.github.solmyr.jicl.StringsKey;
import com.github.solmyr.jicl.commands.instantiation.CommandInstantiator;
import com.github.solmyr.jicl.specialcommands.ExitCommand;
import com.github.solmyr.jicl.specialcommands.GlobalHelpCommand;
import com.github.solmyr.jicl.specialcommands.HelpCommand;
import com.github.solmyr.jicl.specialcommands.PrintCommand;

public class CommandManager {
	private Map<String, Class<?>> commandMap;
	private final String HELP_KEYWORD;
	private final String EXIT_KEYWORD;

	private final CommandInstantiator commandInstantiator;
	private final PrintStream outputStream;

	public CommandManager(CommandLineConfig clc) {
		this.commandInstantiator = clc.getCommInstantiator();
		this.HELP_KEYWORD = Strings.m(StringsKey.HELP_KEYWORD);
		this.EXIT_KEYWORD = Strings.m(StringsKey.EXIT_KEYWORD);
		this.outputStream = clc.getOutputStream();

		CommandRegistrator registrator = new CommandRegistrator(clc);
		commandMap = registrator.registerMap();
	}

	
	public Set<String> getCommandNames() {
		return commandMap.keySet();
	}

	public ICommand getCommandInstance(String line) {
		String[] arguments = line.split("\\s+");
		String commandName = arguments[0].trim();

		if (commandName.equals(HELP_KEYWORD)) {
			return helpCommand(arguments);
		}

		if (commandName.equals(EXIT_KEYWORD)) {
			ICommand command = new ExitCommand();
			command.init(outputStream);
			return command;
			
		}

		if (commandMap.containsKey(commandName)) {
			Class<?> commandClazz = commandMap.get(commandName);
			try {
				arguments = Arrays.copyOfRange(arguments, 1, arguments.length);

				ICommand commandInstance = commandInstantiator.createInstance(commandClazz);
				commandInstance.init(outputStream);
				
				new JCommander(commandInstance, arguments);
				return commandInstance;

			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return null;
		}

	}


	private ICommand helpCommand(String[] arguments) {
		if (arguments.length == 1) {
			ICommand command = new GlobalHelpCommand(commandMap, HELP_KEYWORD, EXIT_KEYWORD);
			command.init(outputStream);
			return command;
		} else if (arguments.length == 2) {
			ICommand command = new HelpCommand(commandMap.get(arguments[1]));
			command.init(outputStream);
			return command;
		} else {
			ICommand command = new PrintCommand(Strings.m(StringsKey.BAD_HELP_COMMAND_USAGE) + " " + HELP_KEYWORD);
			command.init(outputStream);
			return command;
		}
	}

}
