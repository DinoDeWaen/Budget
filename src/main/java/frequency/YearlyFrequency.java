package frequency;

import frequency.Frequency;

/**
 * Created by dino on 08/11/14.
 */
public class YearlyFrequency extends Frequency {
    public YearlyFrequency(double amount) {
        super(amount);
    }

    @Override
    public double getMonthlyAmount() {
        return amount /12;
    }

    @Override
    public double getYearlyAmount() {
        return amount;
    }
}
