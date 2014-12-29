package budget;
import DB.BudgetDataBase;
import category.Category;

/**
 * Created by dino on 08/11/14.
 */
public  class AddBudgetService implements BudgetServices {

    public Integer addBudget(BudgetDTO budgetDTO) {
        final Budget budget = buildBudget(budgetDTO);
        return BudgetDataBase.budgetDataBase.addBudget(budget);
    }

    private Budget buildBudget(BudgetDTO budgetDTO) {
        final Category category = BudgetDataBase.budgetDataBase.getCategory(budgetDTO.getCategoryId()); 
        final BudgetLine budgetLine = BudgetLine.newBuilder()
        		.withBudgetAmount(budgetDTO.getAmount())
        		.withBeginDate(budgetDTO.getBeginDate())
        		.withEndDate(budgetDTO.getEndDate())
        		.withDueDate(budgetDTO.getDueDate())        		
                .withNumberOfMonthsBetweenDueDates(budgetDTO.getNumberOfMonthsBetweenDueDates()) 
                .build();
        
        return Budget.newBuilder()
                .withName(budgetDTO.getName())  
                .withBudgetLine(budgetLine)
                .withCategory(category)
                .build();
    }
}
