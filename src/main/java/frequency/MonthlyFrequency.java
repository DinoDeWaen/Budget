package frequency;

import frequency.Frequency;

/**
 * Created by dino on 08/11/14.
 */
public class MonthlyFrequency extends Frequency {
    public MonthlyFrequency(double amount) {
        super(amount);
    }

    @Override
    public double getMonthlyAmount() {
        return amount;
    }

    @Override
    public double getYearlyAmount() {
        return amount * 12;
    }
}
