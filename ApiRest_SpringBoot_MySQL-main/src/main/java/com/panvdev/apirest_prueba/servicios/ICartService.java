package com.panvdev.apirest_prueba.servicios;

import java.util.List;

import com.panvdev.apirest_prueba.modelos.Cart;
import com.panvdev.apirest_prueba.modelos.User;

public interface ICartService {
	public List<Cart> geCarts();
	public Cart save(Cart cart);
	public Cart GetById(long id);
	public void eliminar(long id);
}
