package com.panvdev.apirest_prueba.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panvdev.apirest_prueba.modelos.Order;
import com.panvdev.apirest_prueba.modelos.Payment;

public interface PaymentsRepository extends JpaRepository<Payment,Long> {
    boolean existsById(long id);
    
}