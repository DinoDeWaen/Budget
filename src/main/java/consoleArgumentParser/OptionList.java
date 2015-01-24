package consoleArgumentParser;

import java.util.HashMap;
import java.util.Map;

public class OptionList {
	private final Map<String, Option> options = new HashMap<String, Option>();
	
	public boolean hasOption(final String id){
		return options.containsKey(id);
	}
	
	public boolean hasParameter(final String id){
		return options.get(id).hasParameter();
	}
	public void addOptionByName(final String id, final Option opt){
		options.put(id,  opt);	
	}
}
