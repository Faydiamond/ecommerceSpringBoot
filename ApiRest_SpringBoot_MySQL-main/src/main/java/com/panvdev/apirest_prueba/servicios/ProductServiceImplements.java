package com.panvdev.apirest_prueba.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.excepciones.ItemAlreadyExists;
import com.panvdev.apirest_prueba.modelos.Product;
import com.panvdev.apirest_prueba.modelos.User;
import com.panvdev.apirest_prueba.repositorios.ProductRepository;

@Service
public class ProductServiceImplements implements IProductService {
	@Autowired
	ProductRepository proService;
	@Override
	public List<Product> getCategories() {
		try {
			return proService.findAll();
		} catch (DataAccessException ex) {
			throw new ApiRequestException("Error al acceder a la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Product save(Product product) {
		try {
			
			return proService.save(product);

		} catch (DataAccessException ex) {
			throw new ApiRequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Product GetById(long id) {
		Optional<Product> optionalProduct = proService.findById(id);
		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			throw new ApiRequestException("Producto no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void eliminar(long id) {
		Optional<Product> optionalProductr = proService.findById(id);
		if (optionalProductr.isPresent()) {
			proService.deleteById(id);
		} else {
			throw new ApiRequestException("Producto no encontrado", HttpStatus.NOT_FOUND);
		}
		
	}

}
