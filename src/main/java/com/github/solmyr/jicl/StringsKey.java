package com.github.solmyr.jicl;

public enum StringsKey {
	HELP_KEYWORD("keywords.help"),
	EXIT_KEYWORD("keywords.exit"),
	UNKNOWN_COMMAND_MESSAGE("messages.unknown_command"),
	PROMPT_SYMBOL("prompt.start_symbol"), 
	BAD_HELP_COMMAND_USAGE("message.bad_help_command_usage"), 
	CLOSING("message.closing_console"), 
	HELP_POSIBLE_COMMANDS("message.posible_commands"), 
	COMMAND("message.commmand"), MESSAGE_END_CONSOLE("message.end_console"), USAGE("message.usage")
	;
	
	private String key;
	
	public String getKey() {
		return key;
	}

	private StringsKey(String key) {
		this.key = key;
	}
}
