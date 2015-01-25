package consoleapplication;

import consoleArgumentParser.ArgumentList;
import consoleArgumentParser.ArgumentParser;
import consoleArgumentParser.Option;
import consoleArgumentParser.OptionList;
import consoleArgumentParser.OptionType;

public class BudgetTool {	
	private static final Option OPTION_ADD_CATEGORY = Option.newBuilder().withName("addCategory").withType(OptionType.ONE_PARAMETER).build();
	
	private static final OptionList options = new OptionList ();
	static {
		options.addOptionByName("addCategory", OPTION_ADD_CATEGORY);
	}	
	
	public static void main(String[] args) {		
		final ArgumentList arguments = new ArgumentParser().parse(args, options);
		
		if (arguments.hasArgument("addCategory")){
		    System.out.print("add category");	
		}
	}
}
