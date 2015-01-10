package cashFlow;

import org.joda.time.DateTime;

import cashFlowTypes.CashFlowType;

public class CashFlow {
	private Integer id;	
	private double amount;
	private CashFlowType type;
	private DateTime date;

	public double getAmount() {
		return amount;
	}

	public CashFlowType getType() {
		return type;
	}

	public DateTime getDate() {
		return date;
	}

	public Integer getId() {
		return id;
	}
	
	private CashFlow(Builder builder) {
		this.amount = builder.amount;
		this.type = builder.type;
		this.date = builder.date;
		this.id = builder.id;
	}
	
    public static Builder newBuilder() {
        return new Builder();
    }

	public static final class Builder {
		private double amount;
		private CashFlowType type;
		private DateTime date;
		private Integer id;

		public Builder withAmount(double amount) {
			this.amount = amount;
			return this;
		}

		public Builder withType(CashFlowType type) {
			this.type = type;
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

		public CashFlow build() {
			return new CashFlow(this);
		}
	}
}
