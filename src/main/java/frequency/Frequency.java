package frequency;
/**
 * Created by dino on 08/11/14.
 */
public abstract class Frequency {

    protected Frequency() {
    }
    public abstract double getMonthlyAmount(double amount);
    public abstract double getYearlyAmount(double amount);
}
