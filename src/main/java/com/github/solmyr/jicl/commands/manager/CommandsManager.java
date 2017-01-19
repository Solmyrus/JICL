package com.github.solmyr.jicl.commands.manager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

import org.reflections.Reflections;

import com.beust.jcommander.JCommander;
import com.github.solmyr.jicl.CommandLineConfig;
import com.github.solmyr.jicl.commands.instantiation.CommandInstantiator;
import com.github.solmyr.jicl.specialcommands.ExitCommand;
import com.github.solmyr.jicl.specialcommands.GlobalHelpCommand;
import com.github.solmyr.jicl.specialcommands.HelpCommand;
import com.github.solmyr.jicl.specialcommands.PrintCommand;

public class CommandsManager {
	private Map<String, Class<?>> commandMap;
	private final String HELP_KEYWORD;
	private final String EXIT_KEYWORD;

	private final CommandInstantiator commandInstantiator;

	public CommandsManager(CommandLineConfig clc) {
		this.commandInstantiator = clc.getCommInstantiator();
		this.HELP_KEYWORD = clc.getHelpKeyword();
		this.EXIT_KEYWORD = clc.getExitKeyword();

		initCommandMap(clc);

	}

	private void initCommandMap(CommandLineConfig clc) {
		commandMap = new HashMap<String, Class<?>>();
		for (String pckg : clc.getCommandsPackage()) {
			Reflections reflections = new Reflections(pckg);
			Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Command.class);
			for (Class<?> clazz : annotated) {
				if (!ICommand.class.isAssignableFrom(clazz))
					continue;

				Command command = clazz.getAnnotation(Command.class);
				commandMap.put(command.name(), clazz);
			}
		}
	}

	public Set<String> getCommandNames() {
		return commandMap.keySet();
	}

	public ICommand getCommandInstance(String line) {
		String[] arguments = line.split("\\s+");
		String commandName = arguments[0];

		if (commandName.equals(HELP_KEYWORD)) {
			if (arguments.length == 1) {
				GlobalHelpCommand command = new GlobalHelpCommand(commandMap, HELP_KEYWORD, EXIT_KEYWORD);
				return command;
			} else if (arguments.length == 2) {
				HelpCommand command = new HelpCommand(commandMap.get(arguments[1]));
				return command;
			} else {
				return new PrintCommand("Spatne pouziti prikazu " + HELP_KEYWORD);
			}
		}

		if (commandName.equals(EXIT_KEYWORD)) {
			return new ExitCommand();
		}

		if (commandMap.containsKey(commandName)) {
			Class<?> commandClazz = commandMap.get(commandName);
			try {
				arguments = Arrays.copyOfRange(arguments, 1, arguments.length);

				ICommand commandInstance = commandInstantiator.createInstance(commandClazz);

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

}
