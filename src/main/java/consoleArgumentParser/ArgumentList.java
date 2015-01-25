package consoleArgumentParser;

import java.util.HashMap;
import java.util.Map;

public class ArgumentList {
	private final Map<String, Argument> arguments = new HashMap<String, Argument>();
	
	public String getArgumentValue(final String id) {
		return arguments.get(id).getValue();
	}
	public void addArgumentValue(final String id, final String value) {
		arguments.get(id).addValue(value);
	}
	public boolean hasArgument(final String id) {
		return arguments.containsKey(id);
	}
	public void addArgument(final String id, final Argument arg){
		arguments.put(id,  arg);	
	}
}
