package com.panvdev.apirest_prueba.servicios;

import java.util.List;

import com.panvdev.apirest_prueba.modelos.Category;

public interface ICategoryService {
	public List<Category> getCategories();
	public Category save(Category category);
	public Category GetById(long id);
	public void eliminar(long id);
}
