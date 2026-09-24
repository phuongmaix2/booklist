package hw2.booklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import hw2.booklist.domain.Book;
import hw2.booklist.domain.BookRepository;
import hw2.booklist.domain.Category;
import hw2.booklist.domain.CategoryRepository;

@SpringBootApplication
public class BooklistApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooklistApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeDB(
            BookRepository bookRepository,
            CategoryRepository categoryRepository) {

        return (args) -> {

            Category fantasy = new Category("Fantasy");
            Category romance = new Category("Romance");
            Category mystery = new Category("Mystery");

            categoryRepository.save(fantasy);
            categoryRepository.save(romance);
            categoryRepository.save(mystery);

            Book book1 = new Book(
                    "The Great Gatsby",
                    "F. Scott Fitzgerald",
                    1925,
                    "9780743273565",
                    10.99);

            book1.setCategory(romance);

            Book book2 = new Book(
                    "To Kill a Mockingbird",
                    "Harper Lee",
                    1960,
                    "9780061120084",
                    7.99);

            book2.setCategory(mystery);

            Book book3 = new Book(
                    "1984",
                    "George Orwell",
                    1949,
                    "9780451524935",
                    8.99);

            book3.setCategory(fantasy);

            Book book4 = new Book(
                    "Pride and Prejudice",
                    "Jane Austen",
                    1813,
                    "9780141439518",
                    9.49);

            book4.setCategory(romance);

            Book book5 = new Book(
                    "The Hobbit",
                    "J.R.R. Tolkien",
                    1937,
                    "9780547928227",
                    12.99);

            book5.setCategory(fantasy);

            bookRepository.save(book1);
            bookRepository.save(book2);
            bookRepository.save(book3);
            bookRepository.save(book4);
            bookRepository.save(book5);
        };
    }
}