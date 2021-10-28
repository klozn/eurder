package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.users.Admin;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class AdminRepo {

    private final Set<Admin> admins = new HashSet<>();

    public AdminRepo() {
        save(new Admin.Builder()
                .withId("1")
                .withFirstname("Klaas")
                .withLastname("Devits")
                .withEmail("admin@eurder.com")
                .build());
    }

    public Set<Admin> getAll() {
        return Collections.unmodifiableSet(admins);
    }

    public boolean save(Admin admin) {
        assertEmailNotRegistered(admin.getEmail());
        return admins.add(admin);
    }

    private void assertEmailNotRegistered(String email) {
        if (getAll().stream()
                .anyMatch(admin -> admin.getEmail().equals(email))) {
            throw new IllegalStateException("The email address (" + email + ") is already registered to an admin.");
        }
    }
}
