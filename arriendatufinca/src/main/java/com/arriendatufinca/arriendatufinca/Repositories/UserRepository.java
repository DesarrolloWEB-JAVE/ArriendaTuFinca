package com.arriendatufinca.arriendatufinca.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arriendatufinca.arriendatufinca.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}