package br.com.tecnodev.repository;

import br.com.tecnodev.entities.role.Role;
import br.com.tecnodev.entities.user.User;
import br.com.tecnodev.repository.util.Builder.RoleBuilder;
import br.com.tecnodev.repository.util.Builder.UserBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail() {
        List<Role> adminRole = List.of(RoleBuilder.adminRole());
        User user = UserBuilder.user(adminRole, "Lucio", "lucio@email.com");
        userRepository.save(user);

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