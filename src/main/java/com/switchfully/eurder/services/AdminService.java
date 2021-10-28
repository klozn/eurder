package com.switchfully.eurder.services;

import com.switchfully.eurder.domain.exceptions.UnauthorizedUserException;
import com.switchfully.eurder.repositories.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepo repo;

    @Autowired
    public AdminService(AdminRepo repo) {
        this.repo = repo;
    }

    public void assertAdminId(String userId) {
        if (repo.getAll().stream().noneMatch(admin -> admin.getId().equals(userId))) {
            throw new UnauthorizedUserException(userId + " is not an admin id.");
        }
    }
}
