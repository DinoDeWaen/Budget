package budget;
import category.Category;
import frequency.Frequency;
import util.BudgetDataBase;

/**
 * Created by dino on 08/11/14.
 */
public abstract class AddBudgetService implements BudgetServices {

    protected abstract Frequency getBudgetLineFrequency();

    public Integer addBudget(BudgetDTO budgetDTO) {
        final Budget budget = buildBudget(budgetDTO);
        return BudgetDataBase.budgetDataBase.addBudget(budget);
    }

    private Budget buildBudget(BudgetDTO budgetDTO) {
        final Frequency frequencyBL = getBudgetLineFrequency();
        final Category category = BudgetDataBase.budgetDataBase.getCategory(budgetDTO.getCategoryId()); 
        return Budget.newBuilder()
        		.withbudgetAmount(budgetDTO.getAmount())
                .withName(budgetDTO.getName())
                .withCategory(category)
                .withFrequency(frequencyBL)
                .build();
    }
}
