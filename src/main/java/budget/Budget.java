package budget;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import cashFlow.CashFlow;
import category.Category;

/**
 * Created by dino on 07/11/14.
 */
public class Budget {
    private Integer id;
    private String name;    
    private BudgetLine budgetLine;
	private Category category;  
	private List<CashFlow> cashFlows;

    private Budget(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.budgetLine = builder.budgetLine;      
        this.category = builder.category;
        this.cashFlows = builder.cashFlows;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isIncome ()
    {  	
    	return budgetLine.isIncome();
    }
    public double getBudgetAmount() {
		return budgetLine.getBudgetAmount();
	} 
    public Interval getBudgetInterval()
    {
    	return budgetLine.getBudgetInterval();
    }
    public DateTime getDueDate() {
		return budgetLine.getDueDate();
	}    
	public Period getperiodBetweenDueDates() {
        return budgetLine.getPeriodBetweenDueDates();
    }
    public Category getCategory() {
        return category;
    } 
    public double getMonthlyBudgetAmount() {
		return budgetLine.getMonthlyBudgetAmount();
	}
    public double getYearlyBudgetAmount() {
    	return budgetLine.getYearlyBudgetAmount();
	}
    public double getBudgetAmount(Interval interval) {
    	return budgetLine.getBudgetAmountInInterval(interval) ;
	}
	public double getBalance() {
		double result = 0.0;
		for (CashFlow cf: cashFlows){
			result += cf.getCashFlowAmount();
		}
		return result;
	}
    
	public void  addCashFlow(CashFlow cashFlow) {
		cashFlows.add(cashFlow);
	}  
    public static Builder newBuilder() {
        return new Builder();
    }    

	public static final class Builder {
		private Integer id;
        private String name;		
    	private BudgetLine budgetLine;    	
        private Category category = Category.emptyCategory;
        private List<CashFlow> cashFlows = new ArrayList<CashFlow>();

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
        public Builder withBudgetLine(BudgetLine budgetLine){
        	if (budgetLine != null){
        		this.budgetLine = budgetLine;
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
