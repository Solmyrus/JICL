package com.github.solmyr.jicl.specialcommands;

import com.github.solmyr.jicl.commands.manager.Command;
import com.github.solmyr.jicl.commands.manager.ICommand;

public class HelpCommand implements ICommand{
	private Class<?> clazz;
	
	public HelpCommand(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void init() {
		
	}

	public void process() {
		if(clazz == null) {
			System.out.println("Neexistujici prikaz");
			return;
		}
		
		Command command = clazz.getAnnotation(Command.class);
		System.out.println(command.name() + " - " + command.description());
		System.out.println("Pouziti: " + command.usage());
		
	}
}
