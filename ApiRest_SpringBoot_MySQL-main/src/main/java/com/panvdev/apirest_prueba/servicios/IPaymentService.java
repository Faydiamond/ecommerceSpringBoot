package com.panvdev.apirest_prueba.servicios;

import java.util.List;

import com.panvdev.apirest_prueba.modelos.Order;
import com.panvdev.apirest_prueba.modelos.Payment;

public interface IPaymentService {
	public List<Payment> getOrders();
	public Payment save(Payment payment);
	public Payment GetById(long id);
	public void eliminar(long id);
}
