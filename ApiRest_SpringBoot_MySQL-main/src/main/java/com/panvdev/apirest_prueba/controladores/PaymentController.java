package com.panvdev.apirest_prueba.controladores;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.modelos.Cart;
import com.panvdev.apirest_prueba.modelos.Order;
import com.panvdev.apirest_prueba.modelos.Payment;
import com.panvdev.apirest_prueba.respuestas.ApiResponse;
import com.panvdev.apirest_prueba.servicios.OrderServiceImplements;
import com.panvdev.apirest_prueba.servicios.PaymentServiceImplements;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:2009")
public class PaymentController {
	@Autowired
	PaymentServiceImplements payService;
	@Autowired
	OrderServiceImplements orderService;

	@GetMapping("/payments")
	public ResponseEntity<ApiResponse> getPays() {
		try {
			List<Payment> orders = payService.getOrders();

			ApiResponse response = ApiResponse.success("Listado de pagos", orders);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	@PostMapping("/payment")
	public ResponseEntity<ApiResponse> savePay(@RequestBody Map<String, String> requestBody ) {
		try {

			Long orderId = Long.parseLong(requestBody.get("order_id"));
			Order orderFound = orderService.GetById(orderId);
			
			if (orderFound == null) {
				throw new ApiRequestException("PAgo no encontrado con el id: " + orderId, HttpStatus.NOT_FOUND);
			}
			
			Payment newPay =  new Payment();
			newPay.setOrder(orderFound);
			newPay.setAmount(new BigDecimal(requestBody.get("amount")));
			newPay.setPayment_method(requestBody.get("payment_method"));
			newPay.setStatus(requestBody.get("status"));
			System.out.print("queee " + newPay);
			ApiResponse response = ApiResponse.success("Pago creado correctamente", payService.save(newPay));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
}
