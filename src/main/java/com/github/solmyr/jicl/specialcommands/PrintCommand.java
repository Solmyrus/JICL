package com.github.solmyr.jicl.specialcommands;

import java.io.PrintStream;

public class PrintCommand extends BasicAbstractCommand{
	private final String MESSAGE;
	
	public PrintCommand(String message) {
		this.MESSAGE = message;
	}

	public PrintCommand(String message, PrintStream printStream) {
		this.MESSAGE = message;
		this.out = printStream;
	}

	public void process() {
		out.println(MESSAGE);
	}

}
