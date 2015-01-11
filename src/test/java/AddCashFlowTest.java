import static org.junit.Assert.*;
import gateway.BudgetDataBase;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import budget.Budget;
import cashFlow.AddCashFlowService;
import cashFlow.CashFlow;
import cashFlow.CashFlowDTO;
import cashFlow.CashflowServices;


public class AddCashFlowTest {
	
	private static final double ACCURACY = 0.00001;
	
	private static CashflowServices cashFlowService;
	private static CashFlowDTO cashFlowDTO;
	private static CashFlow cashFlow;	
	
	private static final double amount = 155.47;
	private static final DateTime date = new DateTime(2015, 1, 10, 0, 0);	
	private static boolean incomeCashFlow;
	private static Integer cashFlowId;

	private static final String budgetName = "budgetName";
	private static Budget budget = Budget.newBuilder().withName(budgetName).build();
	private static Integer budgetId;
	
	@Before
	public void setUp(){
		cashFlowService = new AddCashFlowService();	
		budgetId = BudgetDataBase.budgetDataBase.addBudget(budget);
	}
	
	@Test
	public void addCashFlowIncome_CanBeRetrieved(){
		incomeCashFlow = true;
		
		addAndRetreiveCashFlow();
		
		validateCashFlow();
		
		assertEquals(cashFlow.getCashFlowAmount(), amount,ACCURACY);
	}

	@Test
	public void addCashFlowExpense_CanBeRetrieved(){
		incomeCashFlow = false;
		
		addAndRetreiveCashFlow();
		
		validateCashFlow();
		
		assertEquals(cashFlow.getCashFlowAmount(), amount * -1,ACCURACY);
	}
	
	private void validateCashFlow() {
		assertEquals(cashFlow.getAmount(), amount,ACCURACY);
		assertEquals(cashFlow.getDate(), date);
	}
	
	private void addAndRetreiveCashFlow() {
		cashFlowId = addCashFlow();
		
		cashFlow = BudgetDataBase.budgetDataBase.getCashFlow(cashFlowId);
	}
	private Integer addCashFlow(){
        cashFlowDTO = buildCashFlowDTO();	
		
		return cashFlowService.addCashFlow(cashFlowDTO);	
	}
	
	private CashFlowDTO buildCashFlowDTO() {
		return CashFlowDTO.newBuilder()
				      .withAmount(amount)
				      .withDate(date)
				      .withIncomeCashFlow(incomeCashFlow)
				      .withBudgetId(budgetId)
				      .build();
	}

}
