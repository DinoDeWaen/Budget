package frequency;

import frequency.Frequency;

/**
 * Created by dino on 08/11/14.
 */
public class YearlyFrequency extends Frequency {

	@Override
	public double getMonthlyAmount(double amount) {
		return amount /12;
	}

	@Override
	public double getYearlyAmount(double amount) {
		return amount;
	}
}
