package consoleApplication;

import java.util.List;
import java.util.Scanner;

import category.CategoryDTO;
import category.CategoryServiceImpl;
import category.CategoryServices;
import consoleArgumentParser.ArgumentList;

public class CategoryMenu{

	public void addCategory() {
		addCategory(askForCategoryName());	
	}

	private String askForCategoryName() {
		String name = "";
		System.out.println("Enter category name: ");
		
		try (Scanner in = new Scanner(System.in)) {
			name = in.nextLine();		
		}
				
		return name;
	}
	
	public void addCategory(String name) {
		try{
			tryAddCategory(name);
		}
		catch (Exception e){
			System.out.println(e.getMessage());	
		}
	}

	private void tryAddCategory(String name) {
		CategoryServices categoryService =  new CategoryServiceImpl();

        categoryService.addCategory(buildCategoryDTO(name));
        System.out.println(String.format("added category %s",name));		
	}
	
	private CategoryDTO buildCategoryDTO(String name) {
		final CategoryDTO categoryDTO = CategoryDTO.newBuilder()
								             .withCategoryName(name)
								             .build();
		return categoryDTO;
	}
	
	public void printAllCategories() {
		CategoryServices categoryService =  new CategoryServiceImpl();
		List <CategoryDTO> catList = categoryService.getCategories();
		
		for(CategoryDTO cDTO: catList){
			System.out.println(String.format("category: %s", cDTO.getName()));
		}	
	}
}
	
