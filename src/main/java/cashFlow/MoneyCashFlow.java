package cashFlow;

import org.joda.time.DateTime;

import cashFlowTypes.CashFlowType;

public class MoneyCashFlow implements BudgetCashFlow, java.io.Serializable{
	private Integer id;	
	private double amount;
	private CashFlowType cashFlowType;
	private DateTime date;

	public double getAmount() {
		return amount;
	}
	public double calculateSignedCashFlowAmount() {
		return cashFlowType.addSignToCashFlowAmount(getAmount());
	}
	
	public DateTime getDate() {
		return date;
	}
	public Integer getId() {
		return id;
	}
	
	private MoneyCashFlow(Builder builder) {
		this.amount = builder.amount;
		this.cashFlowType = builder.cashFlowType;
		this.date = builder.date;
		this.id = builder.id;
	}
	
    public static Builder newBuilder() {
        return new Builder();
    }

	public static final class Builder {
		private double amount;
		private CashFlowType cashFlowType;
		private DateTime date;
		private Integer id;

		public Builder withAmount(double amount) {
			this.amount = amount;
			return this;
		}

		public Builder withType(CashFlowType cashFlowType) {
			this.cashFlowType = cashFlowType;
			return this;
		}

		public Builder withDate(DateTime date) {
			this.date = date;
			return this;
		}

		public Builder withId(Integer id) {
			this.id = id;
			return this;
		}

		public MoneyCashFlow build() {
			return new MoneyCashFlow(this);
		}
	}
}
