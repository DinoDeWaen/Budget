package budget;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

public class BudgetLine {
	private double budgetAmount;
	private Interval budgetInterval;
	private DateTime dueDate;
	private Period periodBetweenDueDates;

	private BudgetLine(Builder builder) {
		this.budgetAmount = builder.budgetAmount;
		this.budgetInterval = new Interval(builder.beginDate, builder.endDate);
		this.dueDate = builder.dueDate;
		this.periodBetweenDueDates = new Period().withMonths(builder.numberOfMonthsBetweenDueDates);
	}

	public double getBudgetAmount() {
		return budgetAmount;
	}

	public Interval getBudgetInterval() {
		return budgetInterval;
	}

	public DateTime getDueDate() {
		return dueDate;
	}

	public Period getPeriodBetweenDueDates() {
		return periodBetweenDueDates;
	}

	public double getMonthlyBudgetAmount() {
		return budgetAmount / getPeriodBetweenDueDates().getMonths();
	}

	public double getYearlyBudgetAmount() {
		return budgetAmount * 12 / getPeriodBetweenDueDates().getMonths() ;
	}

	public double getBudgetAmountInInterval(Interval interval) {
		return budgetAmount * getNumberOfDueDatesInInterval(interval);
	}

	private double getNumberOfDueDatesInInterval(Interval interval) {
		int numberOfDueDates = 0;
	    DateTime dd = dueDate;

		while (dd.isBefore(interval.getEnd())) {
			if (interval.contains(dd))
				numberOfDueDates++;

			dd = dd.plus(getPeriodBetweenDueDates());
		}

		return numberOfDueDates;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static final class Builder {
		private double budgetAmount;
		private DateTime beginDate;
		private DateTime endDate;
		private DateTime dueDate;
		private Integer numberOfMonthsBetweenDueDates = 1;

		private Builder() {
		}

		public Builder withBudgetAmount(double budgetAmount) {
			this.budgetAmount = budgetAmount;
			return this;
		}

		public Builder withBeginDate(DateTime beginDate) {
			this.beginDate = beginDate;
			return this;
		}

		public Builder withEndDate(DateTime endDate) {
			this.endDate = endDate;
			return this;
		}

		public Builder withDueDate(DateTime dueDate) {
			this.dueDate = dueDate;
			return this;
		}

		public Builder withNumberOfMonthsBetweenDueDates(
				Integer numberOfMonthsBetweenDueDates) {
			if (numberOfMonthsBetweenDueDates != null) {
				this.numberOfMonthsBetweenDueDates = numberOfMonthsBetweenDueDates;
			}
			return this;
		}

		public BudgetLine build() {
			return new BudgetLine(this);
		}
	}
}