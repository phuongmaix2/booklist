package hw2.booklist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import hw2.booklist.domain.Book;
import hw2.booklist.domain.BookRepository;
import hw2.booklist.domain.Category;
import hw2.booklist.domain.CategoryRepository;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository crepository;

    @Test
    public void createNewBook() {
        Category category = new Category("Fantasy");
        crepository.save(category);

        Book book = new Book(
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                1925,
                "9780743273565",
                15.90
        );

        book.setCategory(category);
        repository.save(book);

        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void findBook() {
        Category category = new Category("Fantasy");
        crepository.save(category);

        Book book = new Book(
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                1925,
                "9780743273565",
                15.90
        );

        book.setCategory(category);
        repository.save(book);

        Optional<Book> foundBook = repository.findById(book.getId());

        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getTitle()).isEqualTo("The Great Gatsby");
        assertThat(foundBook.get().getAuthor()).isEqualTo("F. Scott Fitzgerald");
    }

    @Test
    public void deleteNewBook() {
        Category category = new Category("Fantasy");
        crepository.save(category);

        Book book = new Book(
                "Test Book",
                "Test Author",
                2025,
                "123456789",
                10.00
        );

        book.setCategory(category);
        repository.save(book);

        repository.delete(book);

        Optional<Book> deletedBook = repository.findById(book.getId());

        assertThat(deletedBook).isEmpty();
    }
}