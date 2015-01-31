package consoleApplication;

import java.util.List;
import java.util.Scanner;

import category.CategoryDTO;
import category.CategoryServiceImpl;
import category.CategoryServices;

public class CategoryMenu{

	public void addCategory() {
		addCategory(askForCategoryName());	
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
	public void deleteCategory() {
		deleteCategory(askForCategoryName());	
	}
	public void deleteCategory(String name) {
		try{
			tryDeleteCategory(name);
		}
		catch (Exception e){
			System.out.println(e.getMessage());	
		}		
	}
	private void tryDeleteCategory(String name) {
		CategoryServices categoryService =  new CategoryServiceImpl();

        categoryService.deleteCategory(buildCategoryDTO(name));
        System.out.println(String.format("deleted category %s",name));		
	}
	public void printAllCategories() {
		CategoryServices categoryService =  new CategoryServiceImpl();
		List <CategoryDTO> catList = categoryService.getCategories();
		
		System.out.println(String.format("%d categories found", catList.size()));
		
		for(CategoryDTO cDTO: catList){
			System.out.println(String.format("category: %s", cDTO.getName()));
		}	
	}
	private String askForCategoryName() {
		String name = "";
		System.out.println("Enter category name: ");
		
		try (Scanner in = new Scanner(System.in)) {
			name = in.nextLine();		
		}
				
		return name;
	}
	private CategoryDTO buildCategoryDTO(String name) {
		final CategoryDTO categoryDTO = CategoryDTO.newBuilder()
								             .withCategoryName(name)
								             .build();
		return categoryDTO;
	}
}
	
