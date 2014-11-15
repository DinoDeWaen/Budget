/**
 * Created by dino on 11/11/14.
 */
public class CategoryDTO {
    //TODO Je Id meegeven vanit je request is wel erg raar/gevaarlijk
    private Integer id;
    private String name;

    private CategoryDTO(Builder builder) {
        setId(builder.id);
        setName(builder.name);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {
        private Integer id;
        private String name;

        private Builder() {
        }

        public Builder withCategoryId(Integer id) {
            this.id = id;
            return this;
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
