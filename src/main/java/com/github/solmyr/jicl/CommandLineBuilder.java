package com.github.solmyr.jicl;

import com.github.solmyr.jicl.commands.instantiation.BasicCommandInstantiator;
import com.github.solmyr.jicl.commands.instantiation.CommandInstantiator;
import com.github.solmyr.jicl.commands.manager.ICommand;

public class CommandLineBuilder {
	private CommandLineConfig clc;
	public CommandLineBuilder() {
		clc = new CommandLineConfig();
		clc.setCommInstantiator(new BasicCommandInstantiator());
		clc.setOutputStream(System.out);
	}
	
	public CommandLine build() {
		return new CommandLine(clc);
	}
	
	public CommandLineBuilder promptSymbol(String symbol) {
		clc.getStrings().put(StringsKey.PROMPT_SYMBOL.getKey(), symbol);
		return this;
	}
	
	public CommandLineBuilder addCommandPackage(String pck) {
		clc.getCommandsPackage().add(pck);
		return this;
	}
	
	public CommandLineBuilder addCommandPackage(CommandInstantiator commInstantiator) {
		clc.setCommInstantiator(commInstantiator);
		return this;
	}
	
	public CommandLineBuilder includeCommand(Class<? extends ICommand> clazz) {
		clc.getIncludedCommands().add(clazz);
		return this;
	}
	
	public CommandLineBuilder excludeCommand(Class<? extends ICommand> clazz) {
		clc.getExcludedCommands().add(clazz);
		return this;
	}
	
	public CommandLineBuilder commandInstantiator(CommandInstantiator inst) {
		clc.setCommInstantiator(inst);
		return this;
	}
	
	public CommandLineBuilder addSpecificString(StringsKey key, String string) {
		clc.getStrings().put(key.getKey(), string);
		return this;
	}
	
	public CommandLineBuilder addSpecificString(String key, String string) {
		clc.getStrings().put(key, string);
		return this;
	}
	
	public CommandLineBuilder stringsFileName(String fileName) {
		clc.setStringsFileName(fileName);
		return this;
	}
	
}
