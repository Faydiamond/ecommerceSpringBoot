package com.panvdev.apirest_prueba.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panvdev.apirest_prueba.modelos.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{
	boolean existsByCategory(String category);
    boolean existsById(long id);
}
