package br.com.tecnodev.repository.util.Builder;

import br.com.tecnodev.entities.role.Role;

public class RoleBuilder {

    private String name;

    public RoleBuilder(String name) {
        this.name = name;
    }

    public RoleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Role create() {
        return new Role(name);
    }

    public static Role adminRole() {
        Role adminRole = new RoleBuilder("ADMIN_ROLE")
                .create();
        return adminRole;
    }

    public static Role studentRole() {
        Role studentRole = new RoleBuilder("STUDENT_ROLE")
                .create();
        return studentRole;
    }
}


