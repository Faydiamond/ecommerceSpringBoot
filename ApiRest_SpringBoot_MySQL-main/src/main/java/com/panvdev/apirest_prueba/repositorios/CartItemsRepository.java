package com.panvdev.apirest_prueba.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panvdev.apirest_prueba.modelos.CartItems;

public interface CartItemsRepository extends JpaRepository<CartItems,Long>{

}
