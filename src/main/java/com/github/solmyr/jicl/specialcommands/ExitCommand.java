package com.github.solmyr.jicl.specialcommands;

import com.github.solmyr.jicl.Strings;
import com.github.solmyr.jicl.StringsKey;

public class ExitCommand extends BasicAbstractCommand {

	public void process() {
		out.println(Strings.m(StringsKey.CLOSING));
	}
}
