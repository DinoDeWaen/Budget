package consoleApplication;

import java.util.List;

import category.CategoryDTO;
import category.CategoryServiceImpl;
import category.CategoryServices;

public class CategoryMenu {
	private UserInterface ui;

	public CategoryMenu(UserInterface ui) {
		this.ui = ui;
	}

	public void addCategory() {
		addCategory(askForCategoryName());
	}

	public void addCategory(String name) {
		try {
			tryAddCategory(name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void tryAddCategory(String name) {
		CategoryServices categoryService = new CategoryServiceImpl();

		categoryService.addCategory(buildCategoryDTO(name));
		ui.writeMessage(String.format("added category %s", name));
	}

	public void deleteCategory() {
		deleteCategory(askForCategoryName());
	}

	public void deleteCategory(String name) {
		try {
			tryDeleteCategory(name);
		} catch (Exception e) {
			ui.writeMessage(e.getMessage());
		}
	}

	private void tryDeleteCategory(String name) {
		CategoryServices categoryService = new CategoryServiceImpl();

		categoryService.deleteCategory(buildCategoryDTO(name));
		ui.writeMessage(String.format("deleted category %s", name));
	}

	public void printAllCategories() {
		CategoryServices categoryService = new CategoryServiceImpl();
		List<CategoryDTO> catList = categoryService.getCategories();

		ui.writeMessage(String.format("%d categories found", catList.size()));

		for (CategoryDTO cDTO : catList) {
			ui.writeMessage(String.format("category: %s", cDTO.getName()));
		}
	}

	private String askForCategoryName() {
		return ui.askForString("Enter category name: ");
	}

	private CategoryDTO buildCategoryDTO(String name) {
		final CategoryDTO categoryDTO = CategoryDTO.newBuilder()
				.withCategoryName(name).build();
		return categoryDTO;
	}
}
