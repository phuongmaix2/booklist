package hw2.booklist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import hw2.booklist.domain.Category;
import hw2.booklist.domain.CategoryRepository;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    void createCategory() {
        Category category = repository.save(new Category("Fantasy"));

        assertThat(category.getId()).isNotNull();
        assertThat(repository.findById(category.getId()))
                .get()
                .extracting(Category::getName)
                .isEqualTo("Fantasy");
    }

    @Test
    void findCategory() {
        Category category = repository.save(new Category("Mystery"));

        Optional<Category> foundCategory = repository.findById(category.getId());

        assertThat(foundCategory).isPresent();
        assertThat(foundCategory.get().getName()).isEqualTo("Mystery");
    }

    @Test
    void deleteCategory() {
        Category category = repository.save(new Category("Romance"));

        repository.delete(category);

        assertThat(repository.findById(category.getId())).isEmpty();
    }
}
