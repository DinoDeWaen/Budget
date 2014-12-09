package frequency;

import frequency.Frequency;

/**
 * Created by dino on 08/11/14.
 */
public class MonthlyFrequency extends Frequency {

	@Override
	public double getMonthlyAmount(double amount) {
		// TODO Auto-generated method stub
		return amount;
	}

	@Override
	public double getYearlyAmount(double amount) {
		// TODO Auto-generated method stub
		return amount * 12;
	}
}
