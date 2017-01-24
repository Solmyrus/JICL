package com.github.solmyr.jicl.specialcommands;

public class PrintCommand extends BasicAbstractCommand{
	private final String MESSAGE;
	
	public PrintCommand(String message) {
		this.MESSAGE = message;
	}

	public void process() {
		out.println(MESSAGE);
	}

}
