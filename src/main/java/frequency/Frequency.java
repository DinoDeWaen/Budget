package frequency;
/**
 * Created by dino on 08/11/14.
 */
public abstract class Frequency {
    protected double amount;

    protected Frequency(double amount) {
        this.amount = amount;
    }
    public abstract double getMonthlyAmount();
    public abstract double getYearlyAmount();
}
