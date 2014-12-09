package services;

import model.Frequency;
import model.YearlyFrequency;


/**
 * Created by dino on 08/11/14.
 */
public class AddYearlyBudgetService extends AddBudgetService implements BudgetServices {

    @Override
    protected Frequency getBudgetLineFrequency(double budget) {
        return new YearlyFrequency(budget);
    }
}
