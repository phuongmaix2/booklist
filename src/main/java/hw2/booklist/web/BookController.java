package hw2.booklist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import hw2.booklist.domain.BookRepository;
import org.springframework.ui.Model;

import hw2.booklist.domain.Book;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/booklist")
    public String listBooks(Model model) {
        List<Book> books = (List<Book>) bookRepository.findAll();
        System.out.println(books);
        model.addAttribute("books", books);
        return "booklist";
    }

}
