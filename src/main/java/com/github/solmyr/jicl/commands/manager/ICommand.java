package com.github.solmyr.jicl.commands.manager;

import java.io.PrintStream;

public interface ICommand {
	public void init(PrintStream outputWriter);
	public void process();
}
