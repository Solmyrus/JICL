package com.github.solmyr.jicl.specialcommands;

import java.util.Map;

import com.github.solmyr.jicl.Strings;
import com.github.solmyr.jicl.StringsKey;
import com.github.solmyr.jicl.commands.manager.Command;

public class GlobalHelpCommand extends BasicAbstractCommand {
	private Map<String, Class<?>> commandMap;
	
	public GlobalHelpCommand(Map<String, Class<?>> commandMap, String HELP_KEYWORD, String EXIT_KEYWORD) {
		this.commandMap = commandMap;
	}

	public void process() {
		out.println(Strings.m(StringsKey.HELP_POSIBLE_COMMANDS));
		out.println(Strings.m(StringsKey.HELP_KEYWORD) + " - vypise vsechny prikazy");
		out.println(Strings.m(StringsKey.HELP_KEYWORD) + Strings.m(StringsKey.COMMAND) + " - vypise pouziti prikazu");
		for (Class<?> clazz : commandMap.values()) {
			Command command = clazz.getAnnotation(Command.class);
			out.println(command.name() + " - " + command.description());
		}
		out.println(Strings.m(StringsKey.EXIT_KEYWORD) + Strings.m(StringsKey.MESSAGE_END_CONSOLE));
	}

}
