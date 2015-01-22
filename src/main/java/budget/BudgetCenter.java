package budget;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import cashFlow.BudgetCashFlow;
import category.Category;

/**
 * Created by dino on 07/11/14.
 */
public class BudgetCenter implements Budget{
    private Integer id;
    private String name;    
    private BudgetLine budgetLine;
	private Category category;  
	private List<BudgetCashFlow> cashFlows;

    private BudgetCenter(Builder builder) {
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
		return budgetLine.calculateMonthlyBudgetAmount();
	}
    public double getYearlyBudgetAmount() {
    	return budgetLine.calculateYearlyBudgetAmount();
	}
    public double getBudgetAmount(Interval interval) {
    	return budgetLine.calculateBudgetAmountInInterval(interval) ;
	}
	public double calculateCashFlowBalance() {
		double result = 0.0;
		for (BudgetCashFlow cf: cashFlows){
			result += cf.calculateSignedCashFlowAmount();
		}
		return result;
	}
    
	public void  addCashFlow(BudgetCashFlow cashFlow) {
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
        private List<BudgetCashFlow> cashFlows = new ArrayList<BudgetCashFlow>();

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
        public BudgetCenter build() {
            return new BudgetCenter(this);
        }
	}
}
