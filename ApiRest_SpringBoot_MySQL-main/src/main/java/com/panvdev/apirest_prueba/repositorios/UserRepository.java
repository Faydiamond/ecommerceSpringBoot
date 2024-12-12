package com.panvdev.apirest_prueba.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panvdev.apirest_prueba.modelos.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPassword(String password);
    boolean existsById(long id);
    public Optional<User> findByEmail(String email);
    public Optional<User> findByUsername(String username);

}
