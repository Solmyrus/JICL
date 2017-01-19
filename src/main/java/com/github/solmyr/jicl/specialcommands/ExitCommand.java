package com.github.solmyr.jicl.specialcommands;

import com.github.solmyr.jicl.commands.manager.ICommand;

public class ExitCommand implements ICommand{

	public void init() {
	}

	public void process() {
		System.out.println("Ukoncuji CLI");
		
	}

}
