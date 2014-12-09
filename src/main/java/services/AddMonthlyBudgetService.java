package services;

import model.Frequency;
import model.MonthlyFrequency;


/**
 * Created by dino on 07/11/14.
 */
public class AddMonthlyBudgetService extends AddBudgetService implements BudgetServices {

    @Override
    protected Frequency getBudgetLineFrequency(double budget) {
        return new MonthlyFrequency(budget);
    }
}
