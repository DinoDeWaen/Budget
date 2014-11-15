/**
 * Created by dino on 11/11/14.
 */
public class CategoryDTO {
    private String name;

    private CategoryDTO(Builder builder) {
        setName(builder.name);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {
        private String name;

        private Builder() {
        }

        public Builder withCategoryName(String name) {
            this.name = name;
            return this;
        }

        public CategoryDTO build() {
            return new CategoryDTO(this);
        }
    }
}
