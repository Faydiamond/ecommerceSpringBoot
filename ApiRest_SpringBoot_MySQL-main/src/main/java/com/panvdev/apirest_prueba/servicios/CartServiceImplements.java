package com.panvdev.apirest_prueba.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.modelos.Cart;
import com.panvdev.apirest_prueba.modelos.Product;
import com.panvdev.apirest_prueba.modelos.User;
import com.panvdev.apirest_prueba.repositorios.CartRepository;
import com.panvdev.apirest_prueba.repositorios.CategoryRepository;

@Service
public class CartServiceImplements implements ICartService {
	@Autowired
	CartRepository cartService;

	@Override
	public List<Cart> geCarts() {
		try {
			return cartService.findAll();
		} catch (DataAccessException ex) {
			throw new ApiRequestException("Error al acceder a la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Cart save(Cart cart) {
		try {
			return cartService.save(cart);
		} catch (DataAccessException ex) {
			throw new ApiRequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Cart GetById(long id) {
		Optional<Cart> optionalCart = cartService.findById(id);
		if (optionalCart.isPresent()) {
			return optionalCart.get();
		} else {
			throw new ApiRequestException("Cart no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void eliminar(long id) {
		Optional<Cart> optionalCart = cartService.findById(id);
		if (optionalCart.isPresent()) {
			cartService.deleteById(id);
		} else {
			throw new ApiRequestException("Cart no encontrado", HttpStatus.NOT_FOUND);
		}
	}
}
