package com.panvdev.apirest_prueba.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.excepciones.ItemAlreadyExists;
import com.panvdev.apirest_prueba.modelos.Category;
import com.panvdev.apirest_prueba.modelos.User;
import com.panvdev.apirest_prueba.repositorios.CategoryRepository;


@Service
public class CategoryServiceImplements implements ICategoryService {
	@Autowired
	CategoryRepository catService;

	@Override
	public List<Category> getCategories() {
		try {
			return catService.findAll();
		} catch (DataAccessException ex) {
			throw new ApiRequestException("Error al acceder a la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Category save(Category category) {
		try {
			if (catService.existsByCategory(category.getCategoty())) {
				throw new ItemAlreadyExists("La categoria ya existe.");
			}
			return catService.save(category);
		} catch (DataAccessException ex) {
			throw new ApiRequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Category GetById(long id) {
		Optional<Category> optionalCat = catService.findById(id);
		if (optionalCat.isPresent()) {
			return optionalCat.get();
		} else {
			throw new ApiRequestException("Categoria no encontrada", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void eliminar(long id) {
		Optional<Category> optionalCat = catService.findById(id);
		if (optionalCat.isPresent()) {
			catService.deleteById(id);
		} else {
			throw new ApiRequestException("Usuario no encontrado", HttpStatus.NOT_FOUND);
		}
	}

}
