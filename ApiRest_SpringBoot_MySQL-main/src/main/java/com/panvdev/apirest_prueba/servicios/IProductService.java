package com.panvdev.apirest_prueba.servicios;

import java.util.List;

import com.panvdev.apirest_prueba.modelos.Product;

public interface IProductService {
	public List<Product> getCategories();
	public Product save(Product product);
	public Product GetById(long id);
	public void eliminar(long id);
}
