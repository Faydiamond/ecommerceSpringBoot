package com.panvdev.apirest_prueba.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panvdev.apirest_prueba.modelos.Cart;


public interface CartRepository extends JpaRepository<Cart,Long>{
	
}
