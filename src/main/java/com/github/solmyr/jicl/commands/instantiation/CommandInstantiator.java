package com.github.solmyr.jicl.commands.instantiation;

import com.github.solmyr.jicl.commands.manager.ICommand;

public interface CommandInstantiator {
	public ICommand createInstance(Class<?> clazz)  throws InstantiationException, IllegalAccessException ;
}
