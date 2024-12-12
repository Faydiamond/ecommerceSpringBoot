package com.panvdev.apirest_prueba.servicios;

import java.util.List;

import com.panvdev.apirest_prueba.modelos.Order;

public interface IOrderService {
	public List<Order> getOrders();
	public Order save(Order Order);
	public Order GetById(long id);
	public void eliminar(long id);
}
