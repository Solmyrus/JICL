package com.github.solmyr.jicl.specialcommands;

import java.io.PrintStream;

import com.github.solmyr.jicl.commands.manager.ICommand;

public abstract class BasicAbstractCommand implements ICommand{
	protected PrintStream out;
	
	@Override
	public void init(PrintStream out) {
		this.out = out;
	}
}
