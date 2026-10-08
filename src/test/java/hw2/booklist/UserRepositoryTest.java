package hw2.booklist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import hw2.booklist.domain.User;
import hw2.booklist.domain.UserRepository;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void createUser() {
        User user = repository.save(new User("testuser", "encoded-password", "test@example.com", "USER"));

        assertThat(user.getId()).isNotNull();
        assertThat(repository.findById(user.getId()))
                .get()
                .extracting(User::getUsername)
                .isEqualTo("testuser");
    }

    @Test
    void findUserByUsername() {
        repository.save(new User("searchuser", "encoded-password", "search@example.com", "USER"));

        User foundUser = repository.findByUsername("searchuser");

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("search@example.com");
    }

    @Test
    void deleteUser() {
        User user = repository.save(new User("deleteuser", "encoded-password", "delete@example.com", "USER"));

        repository.delete(user);

        assertThat(repository.findById(user.getId())).isEmpty();
        assertThat(repository.findByUsername("deleteuser")).isNull();
    }
}
