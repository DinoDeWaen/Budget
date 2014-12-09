package budget;

import frequency.Frequency;
import frequency.MonthlyFrequency;


/**
 * Created by dino on 07/11/14.
 */
public class AddMonthlyBudgetService extends AddBudgetService implements BudgetServices {

    @Override
    protected Frequency getBudgetLineFrequency() {
        return new MonthlyFrequency();
    }
}
