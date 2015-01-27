package consoleApplication;

import java.util.List;
import java.util.Scanner;

import category.CategoryDTO;
import category.CategoryServiceImpl;
import category.CategoryServices;
import consoleArgumentParser.ArgumentList;

public class CategoryMenu{

	public void addCategory() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter category name: ");
		String name = sc.nextLine();
		addCategory(name);	
	}
	public void addCategory(String name) {
		CategoryServices categoryService =  new CategoryServiceImpl();
		CategoryDTO categoryDTO = CategoryDTO.newBuilder()
								             .withCategoryName(name)
								             .build();
		
        int catId =  categoryService.addCategory(categoryDTO);
        System.out.println(String.format("added category %s with id %d",name ,catId));	
	}

	public void printAllCategories() {
		CategoryServices categoryService =  new CategoryServiceImpl();
		List <CategoryDTO> catList = categoryService.getCategories();
		
		for(CategoryDTO cDTO: catList){
			System.out.println(String.format("category %s", cDTO.getName()));
		}	
	}
}
	
