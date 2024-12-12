package com.panvdev.apirest_prueba.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panvdev.apirest_prueba.modelos.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
    boolean existsById(long id);
    
}
