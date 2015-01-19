import static org.junit.Assert.*;

import org.junit.Test;

import balance.Balance;


public class BalanceTest {
	
	Balance balance; 
	private static final double ACCURACY = 0.00001;
	
	@Test
	public void createBalanceTest(){
		balance = new Balance ();
		assertEquals(balance.getTotalBudgetIncome(), 2000.00, ACCURACY);
		assertEquals(balance.getTotalBudgetExpense(), 1000.00, ACCURACY); 
		assertEquals(balance.getTotalIncome(), 1582.55, ACCURACY);
		assertEquals(balance.getTotalExpense(), 685.57, ACCURACY); 		
	}

}
