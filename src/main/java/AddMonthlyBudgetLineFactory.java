/**
 * Created by dino on 07/11/14.
 */
public class AddMonthlyBudgetLineFactory implements UseCaseFactory {
    public UseCase createUseCase(){
        return new AddMonthlyBudgetLineUseCase();
    }

}
