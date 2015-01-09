import gateway.BudgetDataBase;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import budget.Budget;
import cashFlow.AddCashFlowService;
import cashFlow.CashFlowDTO;
import cashFlow.CashflowServices;
import category.Category;


public class AddCashFlow {

	private static CashflowServices cashFlowService;
	private static CashFlowDTO cashFlowDTO;
	private static final double amount = 155.47;
	private static final DateTime date = new DateTime(2015, 1, 10, 0, 0);	
	private static boolean income;

	private static final String budgetName = "budgetName";
	private static final Budget budget = Budget.newBuilder().withName(budgetName).build();
	private static Integer budgetId;
	
	@Before
	public void setUp(){
		cashFlowService = new AddCashFlowService();	
		budgetId = BudgetDataBase.budgetDataBase.addBudget(budget);
	}
	
	@Test
	public void addCashFlow_CanBeRetrieved(){
		cashFlowDTO = buildCashFlowDTO();	
	}

	private CashFlowDTO buildCashFlowDTO() {
		return CashFlowDTO.newBuilder()
				      .withAmount(amount)
				      .withDate(date)
				      .withIncome(income)
				      .build();
	}

}
