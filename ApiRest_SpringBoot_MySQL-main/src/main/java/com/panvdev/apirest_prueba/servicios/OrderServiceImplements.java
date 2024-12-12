package com.panvdev.apirest_prueba.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.excepciones.ItemAlreadyExists;
import com.panvdev.apirest_prueba.modelos.CartItems;
import com.panvdev.apirest_prueba.modelos.Order;
import com.panvdev.apirest_prueba.modelos.User;
import com.panvdev.apirest_prueba.repositorios.OrderRepository;

@Service
public class OrderServiceImplements implements IOrderService {

	@Autowired
	OrderRepository orderService;

	@Override
	public List<Order> getOrders() {
		try {
			return orderService.findAll();
		} catch (DataAccessException ex) {
			throw new ApiRequestException("Error al acceder a la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Order save(Order Order) {
		try {
			if (orderService.existsById(Order.getOrder_id())) {
				throw new ItemAlreadyExists("La orden  ya existe.");
			}
			return orderService.save(Order);

		} catch (DataAccessException ex) {
			throw new ApiRequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Order GetById(long id) {
		Optional<Order> optionalOrder = orderService.findById(id);
		if (optionalOrder.isPresent()) {
			return optionalOrder.get();
		} else {
			throw new ApiRequestException("Order  no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void eliminar(long id) {
		// TODO Auto-generated method stub
		Optional<Order> optionalOrder = orderService.findById(id);
		if (optionalOrder.isPresent()) {
			orderService.deleteById(id);
		} else {
			throw new ApiRequestException("Order no encontrado", HttpStatus.NOT_FOUND);
		}
	}

}
