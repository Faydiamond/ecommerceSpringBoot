package com.panvdev.apirest_prueba.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panvdev.apirest_prueba.modelos.Product;

import antlr.collections.List;

public interface ProductRepository extends JpaRepository<Product,Long>{
	//boolean existsByproduct_name(String product_name);
    //boolean existsById(long id);
	//   List findByCategoryId(Long category_id);
}
