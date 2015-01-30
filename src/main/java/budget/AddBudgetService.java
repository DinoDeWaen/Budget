package budget;
import gateway.BudgetDataBase;
import cashFlowTypes.CashFlowType;
import category.Category;

/**
 * Created by dino on 08/11/14.
 */
public  class AddBudgetService implements BudgetServices {

    public Integer addBudget(BudgetDTO budgetDTO) {
        final BudgetCenter budget = buildBudget(budgetDTO);
        return BudgetDataBase.getDB().addBudget(budget);
    }

    private BudgetCenter buildBudget(BudgetDTO budgetDTO) {
        final Category category = BudgetDataBase.getDB().getCategory(budgetDTO.getCategoryId()); 
        final CashFlowType type = CashFlowType.getCashFlowType(budgetDTO.isIncomeCashFlow());
        final BudgetLine budgetLine = BudgetLine.newBuilder()
        		.withBudgetAmount(budgetDTO.getAmount())
        		.withBeginDate(budgetDTO.getBeginDate())
        		.withEndDate(budgetDTO.getEndDate())
        		.withDueDate(budgetDTO.getDueDate())        		
                .withNumberOfMonthsBetweenDueDates(budgetDTO.getNumberOfMonthsBetweenDueDates()) 
                .withCashFlowType(type)
                .build();
        
        return BudgetCenter.newBuilder()
                .withName(budgetDTO.getName())  
                .withBudgetLine(budgetLine)
                .withCategory(category)
                .build();
    }
}
