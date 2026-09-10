package hw2.booklist;

import java.beans.BeanProperty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import hw2.booklist.domain.Book;
import hw2.booklist.domain.BookRepository;

@SpringBootApplication
public class BooklistApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooklistApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeDB(BookRepository bookRepository) {
		return (args) -> {
			bookRepository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "9780743273565", 10.99));
			bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "9780061120084", 7.99));
			bookRepository.save(new Book("1984", "George Orwell", 1949, "9780451524935", 8.99));
		};
	}

}
