package consoleApplication;

import static org.junit.Assert.*;

import java.util.List;

import gateway.BudgetDataBase;

import org.junit.Test;

import category.Category;

public class CategoryMenuTest {
	private final CategoryMenu addMenu = new CategoryMenu (new AddCategoryUI ());
	private final CategoryMenu deleteMenu = new CategoryMenu (new DeleteCategoryUI ());
	private final CategoryMenu printMenu = new CategoryMenu (new PrintCategoryUI ());
	
	private final String categoryName1 = "Name1";
	private final String categoryName2 = "Name2";
	
	private String [] log = new String [20];
	private String [] logTest = new String [20];
	private int logi = 0;
	
	@Test
	public void addCategoryWithNameMenuTest(){
		addMenu.addCategory(categoryName1);
		assertEquals(BudgetDataBase.getDB().getCategory(categoryName1).getName(),categoryName1); 
		deleteMenu.deleteCategory(categoryName1);
		assertNull(BudgetDataBase.getDB().getCategory(categoryName1)); 
	}
	
	@Test
	public void addCategoryWithoutNameMenuTest(){
		addMenu.addCategory();
		assertEquals(BudgetDataBase.getDB().getCategory(categoryName1).getName(),categoryName1); 
		deleteMenu.deleteCategory();
		assertNull(BudgetDataBase.getDB().getCategory(categoryName1)); 
	}
	
	@Test
	public void printCategoryMenuTest(){
		log = new String [20];
		printMenu.addCategory(categoryName1);
		printMenu.addCategory(categoryName2);
		List<Category> cats = BudgetDataBase.getDB().getCategories();
		logi = 0;
		
		printMenu.printAllCategories();		
		
		assertArrayEquals(createMessageArray(cats), log);	
	}

	private String [] createMessageArray(List<Category> cats ) {
		int i = 0;
		logTest[i++] = "2 categories found";
		for (Category c :cats){
			logTest[i++] = String.format("category: %s", c.getName());
		}
		return logTest;
	}
	public void addToLog(String message){
		log[logi++] = message;		
	}
	
	private class AddCategoryUI implements UserInterface{
		@Override
		public void writeMessage(String Message) {
			assertEquals(Message, String.format(String.format("added category %s", categoryName1)));
		}

		@Override
		public String askForString(String message) {
			return categoryName1;
		}
	}
	private class DeleteCategoryUI implements UserInterface{
		@Override
		public void writeMessage(String Message) {
			assertEquals(Message, String.format(String.format("deleted category %s", categoryName1)));
		}

		@Override
		public String askForString(String message) {
			return categoryName1;
		}
	}
	private class PrintCategoryUI implements UserInterface{
		@Override
		public void writeMessage(String Message) {
			addToLog(Message);
		}

		@Override
		public String askForString(String message) {
			return "";
		}
	}
}
