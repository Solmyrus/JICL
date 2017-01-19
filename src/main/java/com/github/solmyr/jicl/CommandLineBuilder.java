package com.github.solmyr.jicl;

import com.github.solmyr.jicl.commands.instantiation.BasicCommandInstantiator;
import com.github.solmyr.jicl.commands.instantiation.CommandInstantiator;

public class CommandLineBuilder {
	private CommandLineConfig clc;
	private static final String DEFAULT_PROMPT_SYMBOL = ">";
	private static final String DEFAULT_UNKNOWN_COMMAND_MESSAGE = "!!! Neznamy prikaz !!!";
	private static final String DEFAULT_HELP_KEYWOD = "help";
	private static final String DEFAULT_EXIT_KEYWORD = "exit";
	
	public CommandLineBuilder() {
		clc = new CommandLineConfig();
		clc.setPromptSymbol(DEFAULT_PROMPT_SYMBOL);
		clc.setUnknownCommandMessage(DEFAULT_UNKNOWN_COMMAND_MESSAGE);
		clc.setExitKeyword(DEFAULT_EXIT_KEYWORD);
		clc.setHelpKeyword(DEFAULT_HELP_KEYWOD);
		clc.setCommInstantiator(new BasicCommandInstantiator());
	}
	
	public CommandLine build() {
		return new CommandLine(clc);
	}
	
	public CommandLineBuilder promptSymbol(String symbol) {
		clc.setPromptSymbol(symbol);
		return this;
	}
	
	public CommandLineBuilder helpKeyword(String keyword) {
		clc.setHelpKeyword(keyword);
		return this;
	}
	
	public CommandLineBuilder exitKeyword(String keyword) {
		clc.setExitKeyword(keyword);
		return this;
	}
	
	public CommandLineBuilder unknownCommandMessage(String message) {
		clc.setUnknownCommandMessage(message);
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
	
	
	
	
}
