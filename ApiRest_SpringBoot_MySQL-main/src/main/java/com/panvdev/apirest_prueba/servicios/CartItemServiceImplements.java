package com.panvdev.apirest_prueba.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.modelos.Cart;
import com.panvdev.apirest_prueba.modelos.CartItems;
import com.panvdev.apirest_prueba.repositorios.CartItemsRepository;
import com.panvdev.apirest_prueba.repositorios.CartRepository;

@Service
public class CartItemServiceImplements implements ICartItemsService{
	@Autowired
	CartItemsRepository cartIemService;

	
	@Override
	public List<CartItems> geCartsItems() {
		try {
			return cartIemService.findAll();
		} catch (DataAccessException ex) {
			throw new ApiRequestException("Error al acceder a la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CartItems save(CartItems cartIem) {
		try {
			return cartIemService.save(cartIem);
		} catch (DataAccessException ex) {
			throw new ApiRequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CartItems GetById(long id) {
		Optional<CartItems> optionalCart = cartIemService.findById(id);
		if (optionalCart.isPresent()) {
			return optionalCart.get();
		} else {
			throw new ApiRequestException("Cart item  no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void eliminar(long id) {
		Optional<CartItems> optionalCart = cartIemService.findById(id);
		if (optionalCart.isPresent()) {
			cartIemService.deleteById(id);
		} else {
			throw new ApiRequestException("Elemento del carrito no encontrado", HttpStatus.NOT_FOUND);
		}
		
	}

}
