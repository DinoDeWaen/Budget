package gateway;

import static org.junit.Assert.*;

import org.junit.Test;

import category.Category;

public class gatewayTest {

	private Integer catId1, catId2;
	
	@Test
	public void test() {
		BudgetDataBase.load();	
		catId1 = BudgetDataBase.budgetDataBase.addCategory(Category.newBuilder().withCategoryName("test").build());
		BudgetDataBase.save();
		catId2 = BudgetDataBase.budgetDataBase.addCategory(Category.newBuilder().withCategoryName("test2").build());
		BudgetDataBase.load();	
		assertEquals(BudgetDataBase.budgetDataBase.getCategory(catId1).getName(), "test");
		assertNull(BudgetDataBase.budgetDataBase.getCategory(catId2));
	}

}
