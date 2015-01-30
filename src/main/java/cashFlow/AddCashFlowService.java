package cashFlow;

import cashFlowTypes.CashFlowType;
import gateway.BudgetDataBase;

public class AddCashFlowService implements CashflowServices {

	@Override
	public Integer addCashFlow(CashFlowDTO cashFlowDTO) {
		final CashFlowType type = CashFlowType.getCashFlowType(cashFlowDTO.isIncomeCashFlow());
		final MoneyCashFlow cashFlow = MoneyCashFlow.newBuilder()
				                     .withAmount(cashFlowDTO.getAmount())
				                     .withDate(cashFlowDTO.getDate())
				                     .withType(type)
				                     .build();
		return BudgetDataBase.getDB().addCashFlow(cashFlowDTO.getBudgetId(), cashFlow);
	}
}
