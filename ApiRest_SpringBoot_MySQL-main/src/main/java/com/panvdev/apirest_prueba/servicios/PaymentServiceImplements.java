package com.panvdev.apirest_prueba.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.excepciones.ItemAlreadyExists;
import com.panvdev.apirest_prueba.modelos.Order;
import com.panvdev.apirest_prueba.modelos.Payment;
import com.panvdev.apirest_prueba.repositorios.OrderRepository;
import com.panvdev.apirest_prueba.repositorios.PaymentsRepository;

@Service
public class PaymentServiceImplements  implements IPaymentService{

	@Autowired
	PaymentsRepository payRepo;
	@Override
	public List<Payment> getOrders() {
		try {
			return payRepo.findAll();
		} catch (DataAccessException ex) {
			throw new ApiRequestException("Error al acceder a la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Payment save(Payment payment) {
		try {
			
			if (payRepo.existsById(payment.getPayment_id())) {
				throw new ItemAlreadyExists("El pago  ya existe.");
			}
			return payRepo.save(payment);
		} catch (DataAccessException ex) {
			throw new ApiRequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Payment GetById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(long id) {
		// TODO Auto-generated method stub
		
	}

}
