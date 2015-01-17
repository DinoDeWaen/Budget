package cashFlow;

import cashFlowTypes.CashFlowType;
import gateway.BudgetDataBase;

public class AddCashFlowService implements CashflowServices {

	@Override
	public Integer addCashFlow(CashFlowDTO cashFlowDTO) {
		final CashFlowType type = CashFlowType.getCashFlowType(cashFlowDTO.isIncomeCashFlow());
		final CashFlow cashFlow = CashFlow.newBuilder()
				                     .withAmount(cashFlowDTO.getAmount())
				                     .withDate(cashFlowDTO.getDate())
				                     .withType(type)
				                     .build();
		return BudgetDataBase.budgetDataBase.addCashFlow(cashFlowDTO.getBudgetId(), cashFlow);
	}
}
