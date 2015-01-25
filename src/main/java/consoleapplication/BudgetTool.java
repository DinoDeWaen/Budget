package consoleapplication;

import java.util.Scanner;

import category.AddCategoryService;
import category.CategoryDTO;
import category.CategoryServices;
import consoleArgumentParser.ArgumentList;
import consoleArgumentParser.ArgumentParser;
import consoleArgumentParser.OptionList;
import consoleArgumentParser.OptionType;

public class BudgetTool {	
	
	private static final String ADD_CATEGORY = "addCategory";
	private static final OptionList options = new OptionList ();
	static {
		options.addOption("addCategory", OptionType.ONE_PARAMETER);
	}	
	
	public static void main(String[] args) {		
		final ArgumentList arguments = new ArgumentParser().parse(args, options);
		
		if (arguments.hasArgument(ADD_CATEGORY)){
			String categoryName = arguments.getArgumentValue(ADD_CATEGORY);
			if (categoryName.isEmpty()){
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter category name: ");
			    String iCat = sc.nextLine();
			}
			CategoryServices categoryService =  new AddCategoryService();
			CategoryDTO categoryDTO = CategoryDTO.newBuilder()
									             .withCategoryName(categoryName)
									             .build();
			
	        int catId =  categoryService.addCategory(categoryDTO);
		}
	}
}
