package budget;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import category.Category;

/**
 * Created by dino on 07/11/14.
 */
public class Budget {
    private Integer id;
    private String name;    
    private double budgetAmount;    
    private Interval budgetInterval;
    private DateTime dueDate;    
    private Period periodBetweenDueDates;
    private Category category;    

    private Budget(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.budgetAmount = builder.budgetAmount; 
        this.budgetInterval = new Interval(builder.beginDate, builder.endDate);  
        this.dueDate = builder.dueDate;        
        this.periodBetweenDueDates = new Period().withMonths(builder.numberOfMonthsBetweenDueDates);        
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
    public Interval getBudgetInterval()
    {
    	return budgetInterval;
    }
    public DateTime getDueDate() {
		return dueDate;
	}    
	public Period getperiodBetweenDueDates() {
        return periodBetweenDueDates;
    }
    public Category getCategory() {
        return category;
    }
    
    public double getMonthlyBudgetAmount() {
		return budgetAmount / periodBetweenDueDates.getMonths();
	}
    
    public double getYearlyBudgetAmount() {
    	return getMonthlyBudgetAmount() * 12;
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
        private Integer numberOfMonthsBetweenDueDates = 1;    	
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
        public Builder withNumberOfMonthsBetweenDueDates(Integer numberOfMonthsBetweenDueDates) {
        	if (numberOfMonthsBetweenDueDates != null){
	            this.numberOfMonthsBetweenDueDates = numberOfMonthsBetweenDueDates;
	        }
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
