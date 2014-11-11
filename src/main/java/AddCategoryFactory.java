/**
 * Created by dino on 11/11/14.
 */
public class AddCategoryFactory implements UseCaseFactory {
    @Override
    public UseCase createUseCase() {
        return new AddCategoryUseCase();
    }
}
