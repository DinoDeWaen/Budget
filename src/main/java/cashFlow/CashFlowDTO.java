package cashFlow;

import org.joda.time.DateTime;

import category.CategoryDTO.Builder;

public class CashFlowDTO {
	private double amount;
	private boolean incomeCashFlow;
	private DateTime date;
	private Integer budgetId;
	
	public boolean isIncomeCashFlow() {
		return incomeCashFlow;
	}
	public DateTime getDate() {
		return date;
	}
	public Integer getBudgetId() {
		return budgetId;
	}    
	public double getAmount() {
		return amount;
	}
	
    public static Builder newBuilder() {
        return new Builder();
    }	
	public CashFlowDTO(Builder builder) {
		amount = builder.amount;
		incomeCashFlow = builder.incomeCashFlow;
		date = builder.date;
		budgetId = builder.budgetId;
	}
	
	public static final class Builder {
		private double amount;
		private boolean incomeCashFlow;
		private DateTime date;
		private Integer budgetId;
		
        private Builder() {
        }		

		public Builder withAmount(double amount) {
			this.amount = amount;
			return this;
		}

		public Builder withIncomeCashFlow(boolean incomeCashFlow) {
			this.incomeCashFlow = incomeCashFlow;
			return this;
		}

		public Builder withDate(DateTime date) {
			this.date = date;
			return this;
		}

		public Builder withBudgetId(Integer budgetId) {
			this.budgetId = budgetId;
			return this;
		}

		public CashFlowDTO build() {
			return new CashFlowDTO(this);
		}
	}



}
