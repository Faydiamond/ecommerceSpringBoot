
package com.panvdev.apirest_prueba.servicios;

import java.util.List;

import com.panvdev.apirest_prueba.modelos.CartItems;

public interface ICartItemsService {
	public List<CartItems> geCartsItems();
	public CartItems save(CartItems cartIem);
	public CartItems GetById(long id);
	public void eliminar(long id);
}
