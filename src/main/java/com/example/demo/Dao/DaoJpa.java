package com.example.demo.Dao;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DaoJpa extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);
}
