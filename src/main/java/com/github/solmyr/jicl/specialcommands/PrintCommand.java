package com.github.solmyr.jicl.specialcommands;

import com.github.solmyr.jicl.commands.manager.ICommand;

public class PrintCommand implements ICommand{
	private final String MESSAGE;
	
	public PrintCommand(String message) {
		this.MESSAGE = message;
	}

	public void init() {
	}

	public void process() {
		System.out.println(MESSAGE);
	}

}
