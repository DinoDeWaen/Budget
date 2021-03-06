package budgetModel;

import static org.junit.Assert.*;
import gateway.BudgetDataBase;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import budget.BudgetCenter;
import cashFlow.AddCashFlowService;
import cashFlow.MoneyCashFlow;
import cashFlow.CashFlowDTO;
import cashFlow.CashflowServices;

public class AddCashFlowServiceTest {

	private static final double ACCURACY = 0.00001;

	private static CashflowServices cashFlowService;
	private static CashFlowDTO cashFlowDTO;
	private static MoneyCashFlow cashFlow;

	private static final double amount = 155.47;
	private static final DateTime date = new DateTime(2015, 1, 10, 0, 0);
	private static boolean incomeCashFlow;
	private static Integer cashFlowId;

	private static final String budgetName = "budgetName";
	private static BudgetCenter budget = BudgetCenter.newBuilder()
			.withName(budgetName).build();
	private static Integer budgetId;

	@Before
	public void setUp() {
		cashFlowService = new AddCashFlowService();
		budgetId = BudgetDataBase.getDB().addBudget(budget);
	}

	@Test
	public void addCashFlowIncome_CanBeRetrieved() {
		incomeCashFlow = true;

		addAndRetreiveCashFlow();

		validateCashFlow();

		assertEquals(cashFlow.calculateSignedCashFlowAmount(), amount, ACCURACY);
	}

	@Test
	public void addCashFlowExpense_CanBeRetrieved() {
		incomeCashFlow = false;

		addAndRetreiveCashFlow();

		validateCashFlow();

		assertEquals(cashFlow.calculateSignedCashFlowAmount(), amount * -1,
				ACCURACY);
	}

	private void validateCashFlow() {
		assertEquals(cashFlow.getAmount(), amount, ACCURACY);
		assertEquals(cashFlow.getDate(), date);
	}

	private void addAndRetreiveCashFlow() {
		cashFlowId = addCashFlow();

		cashFlow = BudgetDataBase.getDB().getCashFlow(cashFlowId);
	}

	private Integer addCashFlow() {
		cashFlowDTO = buildCashFlowDTO();

		return cashFlowService.addCashFlow(cashFlowDTO);
	}

	private CashFlowDTO buildCashFlowDTO() {
		return CashFlowDTO.newBuilder().withAmount(amount).withDate(date)
				.withIncomeCashFlow(incomeCashFlow).withBudgetId(budgetId)
				.build();
	}

}
