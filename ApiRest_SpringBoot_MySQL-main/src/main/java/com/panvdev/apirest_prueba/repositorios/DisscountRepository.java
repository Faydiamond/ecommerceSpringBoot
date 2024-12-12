package com.panvdev.apirest_prueba.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panvdev.apirest_prueba.modelos.Disccount;

public interface DisscountRepository extends JpaRepository<Disccount,Long> {
	
    boolean existsById(long id);
}
