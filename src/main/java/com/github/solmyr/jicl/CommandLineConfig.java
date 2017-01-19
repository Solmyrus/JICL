package com.github.solmyr.jicl;

import java.util.ArrayList;
import java.util.List;

import com.github.solmyr.jicl.commands.instantiation.CommandInstantiator;

public class CommandLineConfig {
	private String promptSymbol;
	private String unknownCommandMessage;
	private List<String> commandsPackage = new ArrayList<String>();
	private String helpKeyword;
	private String exitKeyword;
	private CommandInstantiator commInstantiator;
	
	public List<String> getCommandsPackage() {
		return commandsPackage;
	}

	public void setCommandsPackage(List<String> commandsPackage) {
		this.commandsPackage = commandsPackage;
	}

	public String getPromptSymbol() {
		return promptSymbol;
	}

	public void setPromptSymbol(String promptSymbol) {
		this.promptSymbol = promptSymbol;
	}

	public String getUnknownCommandMessage() {
		return unknownCommandMessage;
	}

	public void setUnknownCommandMessage(String unknownCommandMessage) {
		this.unknownCommandMessage = unknownCommandMessage;
	}

	public String getHelpKeyword() {
		return helpKeyword;
	}

	public void setHelpKeyword(String helpKeyword) {
		this.helpKeyword = helpKeyword;
	}

	public String getExitKeyword() {
		return exitKeyword;
	}

	public void setExitKeyword(String exitKeyword) {
		this.exitKeyword = exitKeyword;
	}

	public CommandInstantiator getCommInstantiator() {
		return commInstantiator;
	}

	public void setCommInstantiator(CommandInstantiator commInstantiator) {
		this.commInstantiator = commInstantiator;
	}
	
	
	
	
	

}
