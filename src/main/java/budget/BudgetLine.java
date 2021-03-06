package budget;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import cashFlowTypes.CashFlowType;

public class BudgetLine implements java.io.Serializable {
	private double budgetAmount;
	private Interval budgetInterval;
	private DateTime dueDate;
	private Period periodBetweenDueDates;
	private CashFlowType cashFlowType;

	private BudgetLine(Builder builder) {
		this.budgetAmount = builder.budgetAmount;
		this.budgetInterval = new Interval(builder.beginDate, builder.endDate);
		this.dueDate = builder.dueDate;
		this.periodBetweenDueDates = new Period().withMonths(builder.numberOfMonthsBetweenDueDates);
		this.cashFlowType = builder.cashFlowType;
	}

	public double getBudgetAmount() {
		return budgetAmount;
	}

	public boolean isIncome() {
		return cashFlowType.isIncome();
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

	public double calculateMonthlyBudgetAmount() {
		return budgetAmount / getPeriodBetweenDueDates().getMonths();
	}

	public double calculateYearlyBudgetAmount() {
		return budgetAmount * 12 / getPeriodBetweenDueDates().getMonths() ;
	}

	public double calculateBudgetAmountInInterval(Interval interval) {
		return budgetAmount * calculateNumberOfDueDatesInInterval(interval);
	}
	
	public double calculateMonthlySignedBudgetAmount() {
		return cashFlowType.addSignToCashFlowAmount(calculateMonthlyBudgetAmount());
	}

	public double calculateYearlySignedBudgetAmount() {
		return cashFlowType.addSignToCashFlowAmount(calculateYearlyBudgetAmount());
	}

	public double calculateSignedBudgetAmountInInterval(Interval interval) {
		return cashFlowType.addSignToCashFlowAmount(calculateBudgetAmountInInterval(interval));
	}

	private double calculateNumberOfDueDatesInInterval(Interval interval) {
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
		private int numberOfMonthsBetweenDueDates = 1;
		private CashFlowType cashFlowType; 		

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

		public Builder withNumberOfMonthsBetweenDueDates(int numberOfMonthsBetweenDueDates) {
			if (numberOfMonthsBetweenDueDates > 0) {
				this.numberOfMonthsBetweenDueDates = numberOfMonthsBetweenDueDates;
			}
			return this;
		}
		
		public Builder withCashFlowType(CashFlowType type) {
			this.cashFlowType = type;
			return this;
		}		

		public BudgetLine build() {
			return new BudgetLine(this);
		}
	}
}