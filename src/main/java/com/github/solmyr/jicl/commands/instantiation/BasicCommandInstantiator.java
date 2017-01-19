package com.github.solmyr.jicl.commands.instantiation;

import com.github.solmyr.jicl.commands.manager.ICommand;

public class BasicCommandInstantiator implements CommandInstantiator {

	public ICommand createInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		ICommand commandInstance = (ICommand) clazz.newInstance();
		return commandInstance;
	}

}
