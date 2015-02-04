package gateway;

import static org.junit.Assert.*;

import org.junit.Test;

import category.Category;

public class GatewayTest {

	private Integer catId1, catId2, catId3;
	private final String TEST1 = "test1";
	private final String TEST2 = "test2";
	private final String TEST3 = "test3";
		
	@Test
	public void test() {
		BudgetDataBase.load();	
		catId1 = BudgetDataBase.getDB().addCategory(Category.newBuilder().withCategoryName(TEST1).build());
		BudgetDataBase.save();
		catId2 = BudgetDataBase.getDB().addCategory(Category.newBuilder().withCategoryName(TEST2).build());
		BudgetDataBase.load();	

		assertEquals(BudgetDataBase.getDB().getCategory(catId1).getName(), TEST1);
		assertNull(BudgetDataBase.getDB().getCategory(catId2));
		
		catId2 = BudgetDataBase.getDB().addCategory(Category.newBuilder().withCategoryName(TEST2).build());
		BudgetDataBase.save();
		catId3 = BudgetDataBase.getDB().addCategory(Category.newBuilder().withCategoryName(TEST3).build());
		BudgetDataBase.load();
		

		assertEquals(BudgetDataBase.getDB().getCategory(catId1).getName(), TEST1);		
		assertEquals(BudgetDataBase.getDB().getCategory(catId2).getName(), TEST2);
		assertNull(BudgetDataBase.getDB().getCategory(catId3));
		BudgetDataBase.clear();
		BudgetDataBase.save();
	}

}
