package consoleApplication;

import static org.junit.Assert.*;
import gateway.BudgetDataBase;

import org.junit.Test;

public class CategoryMenuTest {
	private final CategoryMenu addMenu = new CategoryMenu (new AddCategoryUI ());
	private final CategoryMenu deleteMenu = new CategoryMenu (new DeleteCategoryUI ());
	
	private final String categoryName = "Name";
	
	@Test
	public void addCategoryWithNameMenuTest(){
		addMenu.addCategory(categoryName);
		assertEquals(BudgetDataBase.getDB().getCategory(categoryName).getName(),categoryName); 
		deleteMenu.deleteCategory(categoryName);
		assertNull(BudgetDataBase.getDB().getCategory(categoryName)); 
	}
	
	@Test
	public void addCategoryWithoutNameMenuTest(){
		addMenu.addCategory();
		assertEquals(BudgetDataBase.getDB().getCategory(categoryName).getName(),categoryName); 
		deleteMenu.deleteCategory();
		assertNull(BudgetDataBase.getDB().getCategory(categoryName)); 
	}
	
	private class AddCategoryUI implements UserInterface{
		@Override
		public void writeMessage(String Message) {
			assertEquals(Message, String.format("added category %s", categoryName));
		}

		@Override
		public String askForString(String message) {
			return categoryName;
		}
	}
	private class DeleteCategoryUI implements UserInterface{
		@Override
		public void writeMessage(String Message) {
			assertEquals(Message, String.format(String.format("deleted category %s", categoryName)));
		}

		@Override
		public String askForString(String message) {
			return categoryName;
		}
	}
}
