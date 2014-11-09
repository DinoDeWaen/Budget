/**
 * Created by dino on 08/11/14.
 */
public class AddYearlyBudgetLineFactory implements UseCaseFactory  {
    public UseCase CreateUseCase() {
        return new AddYearlyBudgetLineUseCase();
    }
}
