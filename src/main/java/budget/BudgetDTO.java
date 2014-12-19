package budget;

import java.util.Date;

/**
 * Created by dino on 07/11/14.
 */
public class BudgetDTO {
    private String name;
    private double amount;
    private Date beginDate;
    private Date endDate;    
    private Date dueDate;   
    private Integer paymentFrequency;    
    private Integer categoryId;    

    private BudgetDTO(Builder builder) {
        this.name = builder.name;
        this.amount = builder.amount;
        this.beginDate = builder.beginDate;
        this.endDate = builder.endDate;
        this.dueDate = builder.dueDate;
        this.paymentFrequency = builder.paymentFrequency;         
        this.categoryId = builder.categoryId;        
    }

    public static Builder newBuilder() {
        return new Builder();
    }
    public String getName() {
        return name;
    }
    public double getAmount() {
        return amount;
    }
    public Date getBeginDate() {
		return beginDate;
	}
    public Date getEndDate() {
		return endDate;
	}
    public Date getDueDate() {
		return dueDate;
	}    
    public Integer getPaymentFrequency() {
		return paymentFrequency;
	}      
    public Integer getCategoryId() {
        return categoryId;
    }
    
    public static final class Builder {
        private String name;
        private double amount;
        private Date beginDate;
        private Date endDate;    
        private Date dueDate;   
        private Integer paymentFrequency;         
        private Integer categoryId;
        
        private Builder() {
        }
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withAmount(double amount) {
            this.amount = amount;
            return this;
        }
        public Builder withBeginDate(Date beginDate) {
            this.beginDate = beginDate;
            return this;
        }
        public Builder withEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }       
        public Builder withDueDate(Date dueDate) {
            this.dueDate = dueDate;
            return this;
        }       
        public Builder withPaymentFrequency(Integer paymentFrequency) {
            this.paymentFrequency = paymentFrequency;
            return this;
        }        
        public Builder withCategory(Integer categoryId) {
            this.categoryId = categoryId;
            return this;
        }        
        public BudgetDTO build() {
            return new BudgetDTO(this);
        }


    }
}
