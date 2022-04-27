package br.com.tecnodev.repository;

import br.com.tecnodev.entities.user.User;
import br.com.tecnodev.repository.util.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        UserFactory userFactory = new UserFactory(userRepository);
        userFactory.createUser();
    }

    @Test
    void findByEmail() {
        String email = "lucio@email.com";
        Optional<User> userFindByEmail = userRepository.findByEmail(email);

        assertTrue(userFindByEmail.isPresent());
    }

    @Test
    void findByEmail__Should_Return_An_Empty_Optional_When_Is_nonexistent_Email() {
        String email = "nonExistent@email.com";
        Optional<User> userFindByEmail = userRepository.findByEmail(email);

        assertTrue(userFindByEmail.isEmpty());
    }
}