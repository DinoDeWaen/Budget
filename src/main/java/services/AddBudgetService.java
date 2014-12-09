package services;
import model.Budget;
import model.Frequency;
import dto.BudgetDTO;
import util.BudgetDataBase;

/**
 * Created by dino on 08/11/14.
 */
public abstract class AddBudgetService implements BudgetServices {

    protected abstract Frequency getBudgetLineFrequency(double budget);

    public Integer addBudget(BudgetDTO budgetDTO) {
        final Budget budget = buildBudget(budgetDTO);
        return BudgetDataBase.budgetDataBase.addBudget(budget);
    }

    private Budget buildBudget(BudgetDTO budgetDTO) {
        final Frequency frequencyBL = getBudgetLineFrequency(budgetDTO.getAmount());
        return Budget.newBuilder()
                .withName(budgetDTO.getName())
                .withCategory(budgetDTO.getCategory())
                .withFrequency(frequencyBL)
                .build();
    }
}
