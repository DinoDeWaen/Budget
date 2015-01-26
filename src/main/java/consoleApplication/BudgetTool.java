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
	private static final String GET_CATEGORIES = "getCategories";	
	private static final OptionList options = new OptionList ();
	static {
		options.addOption(ADD_CATEGORY, OptionType.ONE_PARAMETER);
		options.addOption(GET_CATEGORIES, OptionType.NO_PARAMETERS);		
	}	
	
	public static void main(String[] args) {
		startUpDB();
		
		final ArgumentList arguments = new ArgumentParser().parse(args, options);
		
		if (arguments.hasArgument(ADD_CATEGORY)){
			String categoryName = arguments.getArgumentValue(ADD_CATEGORY);
			if (categoryName.isEmpty()){
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter category name: ");
			    String iCat = sc.nextLine();
			}
			CategoryServices categoryService =  new CategoryServiceImpl();
			CategoryDTO categoryDTO = CategoryDTO.newBuilder()
									             .withCategoryName(categoryName)
									             .build();
			
	        int catId =  categoryService.addCategory(categoryDTO);
	        System.out.println(String.format("added category with id %d", catId));
		}
		if (arguments.hasArgument(ADD_CATEGORY)){
			CategoryServices categoryService =  new CategoryServiceImpl();
			List <CategoryDTO> catList = categoryService.getCategories();
			
			for(CategoryDTO cDTO: catList){
				System.out.println(String.format("category", cDTO.getName()));
			}
		}
		powerDownDB();		
	}

	private static void powerDownDB() {
		BudgetDataBase.budgetDataBase.save();
	}

	private static void startUpDB() {
		BudgetDataBase.budgetDataBase.load();
	}
}
