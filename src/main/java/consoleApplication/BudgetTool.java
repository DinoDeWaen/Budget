package consoleApplication;

import gateway.BudgetDataBase;
import consoleArgumentParser.ArgumentList;
import consoleArgumentParser.ArgumentParser;
import consoleArgumentParser.OptionList;
import consoleArgumentParser.OptionType;

public class BudgetTool {
	private static final String ADD_CATEGORY = "addCategory";
	private static final String PRINT_CATEGORIES = "printCategories";
	private static final String DELETE_CATEGORY = "deleteCategory";	
	private static final OptionList options = new OptionList();
	static {
		options.addOption(ADD_CATEGORY, OptionType.ONE_PARAMETER);
		options.addOption(PRINT_CATEGORIES, OptionType.NO_PARAMETERS);
		options.addOption(DELETE_CATEGORY, OptionType.ONE_PARAMETER);
	}

	public static void main(String[] args) {
		startUpDB();
		final ArgumentList arguments = new ArgumentParser()
				.parse(args, options);
		if (arguments.hasArgument(ADD_CATEGORY)) {
			if (options.hasParameter(ADD_CATEGORY) && !arguments.getArgumentValue(ADD_CATEGORY).isEmpty())
				new CategoryMenu( new ConsoleUserInterface ()).addCategory(arguments.getArgumentValue(ADD_CATEGORY));
			else
				new CategoryMenu( new ConsoleUserInterface ()).addCategory();
		}
		if (arguments.hasArgument(PRINT_CATEGORIES)) {
			new CategoryMenu( new ConsoleUserInterface ()).printAllCategories();
		}
		if (arguments.hasArgument(DELETE_CATEGORY)) {
			if (options.hasParameter(DELETE_CATEGORY) && !arguments.getArgumentValue(DELETE_CATEGORY).isEmpty())
				new CategoryMenu( new ConsoleUserInterface ()).deleteCategory(arguments.getArgumentValue(DELETE_CATEGORY));
			else
				new CategoryMenu( new ConsoleUserInterface ()).deleteCategory();
		}
		powerDownDB();
	}

	private static void powerDownDB() {
		BudgetDataBase.save();
	}

	private static void startUpDB() {
		BudgetDataBase.load();
	}
}