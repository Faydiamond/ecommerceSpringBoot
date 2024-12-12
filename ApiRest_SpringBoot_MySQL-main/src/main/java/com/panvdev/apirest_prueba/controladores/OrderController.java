package com.panvdev.apirest_prueba.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.modelos.Disccount;
import com.panvdev.apirest_prueba.modelos.Order;
import com.panvdev.apirest_prueba.modelos.User;
import com.panvdev.apirest_prueba.respuestas.ApiResponse;
import com.panvdev.apirest_prueba.servicios.OrderServiceImplements;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:2009")
public class OrderController {
	@Autowired
	OrderServiceImplements orderService;
	
	@GetMapping("/orders")
    public ResponseEntity<ApiResponse> getCategories() {
		 try {
	            List<Order> orders = orderService.getOrders();
	            
	            ApiResponse response = ApiResponse.success("Listado de ordenes", orders);
	            return ResponseEntity.ok(response);
	        } catch (Exception ex) {
	            ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	        }
    }
	
	@GetMapping("/order/{id}")
	public ResponseEntity<ApiResponse> getDiscountId(@PathVariable long id) {
		try {
			Order order = orderService.GetById(id);
            ApiResponse response = ApiResponse.success("Orden", order);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	@PostMapping("/order")
	public ResponseEntity<ApiResponse> saveUser(@RequestBody Order order) {
		try {
			Order newOrder = orderService.save(order);
			ApiResponse response = ApiResponse.success("Orden creada correctamente", newOrder);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long id) {
		try {
			orderService.eliminar(id);
			ApiResponse response = ApiResponse.success("Orden eliminado correctamente",null);
	        return ResponseEntity.ok(response); 
		} catch (ApiRequestException ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			ApiResponse errorResponse = ApiResponse.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
}
