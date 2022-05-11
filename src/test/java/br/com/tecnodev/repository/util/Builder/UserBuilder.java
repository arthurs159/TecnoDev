package br.com.tecnodev.repository.util.Builder;

import br.com.tecnodev.entities.role.Role;
import br.com.tecnodev.entities.user.User;

import java.util.List;

public class UserBuilder {

    private String name;
    private String email;
    private String password;

    private List<Role> roles;

    public UserBuilder(String name, String email, String password, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UserBuilder() {
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withRole(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public User create() {
        return new User(name,
                email,
                password,
                roles
        );
    }

    public static User user(List<Role> roles, String name, String email) {
        User user = new UserBuilder()
                .withName(name)
                .withEmail(email)
                .withPassword("123456")
                .withRole(roles)
                .create();
        return user;
    }
}
