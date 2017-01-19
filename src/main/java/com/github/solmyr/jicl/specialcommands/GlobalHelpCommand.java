package com.github.solmyr.jicl.specialcommands;

import java.util.Map;

import com.github.solmyr.jicl.commands.manager.Command;
import com.github.solmyr.jicl.commands.manager.ICommand;

public class GlobalHelpCommand implements ICommand {
	private Map<String, Class<?>> commandMap;
	private final String HELP_KEYWORD;
	private final String EXIT_KEYWORD;
	
	public GlobalHelpCommand(Map<String, Class<?>> commandMap, String HELP_KEYWORD, String EXIT_KEYWORD) {
		this.commandMap = commandMap;
		this.HELP_KEYWORD = HELP_KEYWORD;
		this.EXIT_KEYWORD = EXIT_KEYWORD;
	}

	public void init() {		
	}

	public void process() {
		System.out.println("Mozne prikazy: ");
		System.out.println(HELP_KEYWORD + " - vypise vsechny prikazy");
		System.out.println(HELP_KEYWORD + " <prikaz> - vypise pouziti prikazu");
		for (Class<?> clazz : commandMap.values()) {
			Command command = clazz.getAnnotation(Command.class);
			System.out.println(command.name() + " - " + command.description());
		}
		System.out.println(EXIT_KEYWORD + " - ukonci konzolu");
	}

}
