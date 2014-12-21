package budget;

import org.joda.time.DateTime;

import category.Category;

/**
 * Created by dino on 07/11/14.
 */
public class Budget {
    private Integer id;
    private String name;    
    private double budgetAmount;    
    private DateTime beginDate;
    private DateTime endDate;    
    private DateTime dueDate;    
    private Integer paymentFrequency;
    private Category category;    

    private Budget(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.budgetAmount = builder.budgetAmount; 
        this.beginDate = builder.beginDate;
        this.endDate = builder.endDate;  
        this.dueDate = builder.dueDate;        
        this.paymentFrequency = builder.paymentFrequency;        
        this.category = builder.category;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getBudgetAmount() {
		return budgetAmount;
	}    
    public DateTime getBeginDate() {
		return beginDate;
	}
    public DateTime getEndDate() {
		return endDate;
	}
    public DateTime getDueDate() {
		return dueDate;
	}    
	public Integer getPaymentFrequency() {
        return paymentFrequency;
    }
    public Category getCategory() {
        return category;
    }
    
    public double getMonthlyBudgetAmount() {
		return budgetAmount * paymentFrequency / 12;
	}
    
    public double getYearlyBudgetAmount() {
    	return budgetAmount * paymentFrequency;
	}
    
    public static Builder newBuilder() {
        return new Builder();
    }    

	public static final class Builder {
		private Integer id;
        private String name;		
    	private double budgetAmount;
        private DateTime beginDate;
        private DateTime endDate;
        private DateTime dueDate;        
        private Integer paymentFrequency;    	
        private Category category = Category.emptyCategory;


        private Builder() {
        }
        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }
        public Builder withName(String name) {
            this.name = name;
            return this;
        }       
        public Builder withBudgetAmount(double budgetAmount) {
            this.budgetAmount= budgetAmount;
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
        public Builder withPaymentFrequency(Integer paymentFrequency) {
            this.paymentFrequency = paymentFrequency;
            return this;
        }          
        public Builder withCategory(Category category) {
            if (category != null) {
                this.category = category;
            }
            return this;
        }  
          
        public Budget build() {
            return new Budget(this);
        }
    }
}
