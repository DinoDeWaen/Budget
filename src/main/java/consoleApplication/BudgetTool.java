package consoleApplication;

import gateway.BudgetDataBase;

import java.util.List;
import java.util.Scanner;

import category.CategoryServiceImpl;
import category.CategoryDTO;
import category.CategoryServices;
import consoleArgumentParser.ArgumentList;
import consoleArgumentParser.ArgumentParser;
import consoleArgumentParser.OptionList;
import consoleArgumentParser.OptionType;

public class BudgetTool {
	private static final String ADD_CATEGORY = "addCategory";
	private static final String PRINT_CATEGORIES = "printCategories";
	private static final OptionList options = new OptionList();
	static {
		options.addOption(ADD_CATEGORY, OptionType.ONE_PARAMETER);
		options.addOption(PRINT_CATEGORIES, OptionType.NO_PARAMETERS);
	}

	public static void main(String[] args) {
		startUpDB();
		final ArgumentList arguments = new ArgumentParser()
				.parse(args, options);
		if (arguments.hasArgument(ADD_CATEGORY)) {
			if (options.hasParameter(ADD_CATEGORY) && !arguments.getArgumentValue(ADD_CATEGORY).isEmpty())
				new CategoryMenu().addCategory(arguments.getArgumentValue(ADD_CATEGORY));
			else
				new CategoryMenu().addCategory();
		}
		if (arguments.hasArgument(PRINT_CATEGORIES)) {
			new CategoryMenu().printAllCategories();
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