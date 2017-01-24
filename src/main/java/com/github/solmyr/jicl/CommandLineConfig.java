package com.github.solmyr.jicl;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.solmyr.jicl.commands.instantiation.CommandInstantiator;

public class CommandLineConfig {
	private CommandInstantiator commInstantiator;
	private String stringsFileName;
	
	public void setStringsFileName(String stringsFileName) {
		this.stringsFileName = stringsFileName;
	}

	private Map<String, String> strings = new HashMap<>();

	private List<String> commandsPackage = new ArrayList<String>();
	private List<Class<?>> includedCommands = new ArrayList<Class<?>>();
	private List<Class<?>> excludedCommands = new ArrayList<Class<?>>();
	
	private PrintStream outputStream;

	public List<String> getCommandsPackage() {
		return commandsPackage;
	}

	public void CommandsPackage(List<String> commandsPackage) {
		this.commandsPackage = commandsPackage;
	}



	public CommandInstantiator getCommInstantiator() {
		return commInstantiator;
	}

	public void setCommInstantiator(CommandInstantiator commInstantiator) {
		this.commInstantiator = commInstantiator;
	}

	public List<Class<?>> getIncludedCommands() {
		return includedCommands;
	}

	public void setIncludedCommands(List<Class<?>> includedCommands) {
		this.includedCommands = includedCommands;
	}

	public List<Class<?>> getExcludedCommands() {
		return excludedCommands;
	}

	public void setExcludedCommands(List<Class<?>> excludedCommands) {
		this.excludedCommands = excludedCommands;
	}

	public PrintStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(PrintStream outputStream) {
		this.outputStream = outputStream;
	}

	public Map<String, String> getStrings() {
		return strings;
	}

	public String getStringsFileName() {
		return stringsFileName;
	}
}
