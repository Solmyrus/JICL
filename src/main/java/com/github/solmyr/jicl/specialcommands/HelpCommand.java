package com.github.solmyr.jicl.specialcommands;

import com.github.solmyr.jicl.Strings;
import com.github.solmyr.jicl.StringsKey;
import com.github.solmyr.jicl.commands.manager.Command;

public class HelpCommand extends BasicAbstractCommand {
	private Class<?> clazz;

	public HelpCommand(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void process() {
		if (clazz == null) {
			out.println(Strings.m(StringsKey.UNKNOWN_COMMAND_MESSAGE));
			return;
		}

		Command command = clazz.getAnnotation(Command.class);
		out.println(command.name() + " - " + Strings.m(command.description()));
		out.println(Strings.m(StringsKey.USAGE) + Strings.m(command.usage()));

	}
}
