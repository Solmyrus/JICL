package com.github.solmyr.jicl.commands.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.github.solmyr.jicl.CommandLineConfig;

public class CommandRegistrator {
	private Map<String, Class<?>> registeredCommands;
	private final CommandLineConfig clc;

	public CommandRegistrator(CommandLineConfig clc) {
		this.clc = clc;
		registeredCommands = new HashMap<>();
	}

	public Map<String, Class<?>> registerMap() {
		registerSpecific();
		registerPackages();
		unregisterSpecific();
		return registeredCommands;
	}

	private void registerSpecific() {
		for (Class<?> commandClazz : clc.getIncludedCommands()) {
			addCommand(commandClazz);
		}
	}

	private void addCommand(Class<?> commandClazz) {
		if (!ICommand.class.isAssignableFrom(commandClazz))
			return;

		Command command = commandClazz.getAnnotation(Command.class);
		if (command == null)
			return;
		registeredCommands.put(command.name(), commandClazz);
	}

	private void registerPackages() {
		for (String pckg : clc.getCommandsPackage()) {
			Reflections reflections = new Reflections(pckg);
			Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Command.class);
			for (Class<?> commandClazz : annotated) {
				addCommand(commandClazz);
			}
		}

	}

	private void unregisterSpecific() {
		for (Class<?> commandClazz : clc.getExcludedCommands()) {
			Command command = commandClazz.getAnnotation(Command.class);
			if (command == null)
				return;
			if (registeredCommands.containsKey(command.name()))
				registeredCommands.remove(command.name());
		}

	}

}
