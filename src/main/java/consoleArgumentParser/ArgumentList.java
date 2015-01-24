package consoleArgumentParser;

import java.util.HashMap;
import java.util.Map;

public class ArgumentList {
	private final Map<String, Argument> arguments = new HashMap<String, Argument>();
	
	public Argument getArgumentByName(final String id) {
		return arguments.get(id);
	}
	public void addArgumentByName(final String id, final Argument arg){
		arguments.put(id,  arg);	
	}
}
