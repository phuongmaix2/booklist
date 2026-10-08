package hw2.booklist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import hw2.booklist.domain.Book;
import hw2.booklist.domain.BookRepository;
import hw2.booklist.domain.Category;
import hw2.booklist.domain.CategoryRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testGetBooks() throws Exception {
        Book book = createBook("REST Test Book");

        this.mockMvc.perform(get("/books").with(user("user").roles("USER")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$[*].title", hasItem(book.getTitle())));
    }

    @Test
    public void testFindBook() throws Exception {
        Book book = createBook("REST Find Test Book");

        this.mockMvc.perform(get("/book/" + book.getId()).with(user("user").roles("USER")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.id").value(book.getId()))
                .andExpect(jsonPath("$.title").value(book.getTitle()));
    }

    private Book createBook(String title) {
        Category category = categoryRepository.save(new Category("REST Test Category"));
        Book book = new Book(title, "REST Test Author", 2025, "REST-" + title, 10.00);
        book.setCategory(category);
        return bookRepository.save(book);
    }
}