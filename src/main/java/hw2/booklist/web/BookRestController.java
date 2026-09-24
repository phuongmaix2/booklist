package hw2.booklist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hw2.booklist.domain.Book;
import hw2.booklist.domain.BookRepository;

@RestController
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/api/rest/books")
    public List<Book> getBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @GetMapping("/api/rest/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}