package br.com.tecnodev.repository.util;

import br.com.tecnodev.entities.role.Role;
import br.com.tecnodev.entities.user.User;
import br.com.tecnodev.repository.UserRepository;
import br.com.tecnodev.repository.util.Builder.RoleBuilder;
import br.com.tecnodev.repository.util.Builder.UserBuilder;

import java.util.List;

public class UserFactory {

    private final UserRepository userRepository;

    public UserFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public void createUser() {
//        List<Role> adminRole = List.of(RoleBuilder.adminRole());
//        User user = UserBuilder.user(adminRole);
//        userRepository.save(user);
//    }
}
