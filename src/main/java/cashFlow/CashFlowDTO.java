package cashFlow;

import category.CategoryDTO.Builder;

public class CashFlowDTO {
	private double amount;
	private boolean income;
	private long date;
	private Integer budgetLineid;
	
    public static Builder newBuilder() {
        return new Builder();
    }
    
	public CashFlowDTO(Builder builder) {
		amount = builder.amount;
		income = builder.income;
		date = builder.date;
		budgetLineid = builder.budgetLineid;
	}
	public static final class Builder {
		private double amount;
		private boolean income;
		private long date;
		private Integer budgetLineid;
		
        private Builder() {
        }		

		public Builder withValue(double amount) {
			this.amount = amount;
			return this;
		}

		public Builder withIncome(boolean income) {
			this.income = income;
			return this;
		}

		public Builder withDate(long date) {
			this.date = date;
			return this;
		}

		public Builder withBudgetLineid(Integer budgetLineid) {
			this.budgetLineid = budgetLineid;
			return this;
		}

		public CashFlowDTO build() {
			return new CashFlowDTO(this);
		}
	}
}
