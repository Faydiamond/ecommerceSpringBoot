package com.panvdev.apirest_prueba.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.modelos.Cart;
import com.panvdev.apirest_prueba.modelos.Category;
import com.panvdev.apirest_prueba.modelos.Product;
import com.panvdev.apirest_prueba.modelos.User;
import com.panvdev.apirest_prueba.respuestas.ApiResponse;
import com.panvdev.apirest_prueba.servicios.CartServiceImplements;
import com.panvdev.apirest_prueba.servicios.UserServiceImplements;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:2009")
public class CartController {
	@Autowired
	CartServiceImplements cartService;
	@Autowired
	UserServiceImplements usrService;
	
	@GetMapping("/carts")
    public ResponseEntity<ApiResponse> getCarts() {
        try {
            List<Cart> carts = cartService.geCarts();
            ApiResponse response = ApiResponse.success("Listado de Carritos", carts);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
	
	@GetMapping("/cart/{id}")
	public ResponseEntity<ApiResponse> getUserId(@PathVariable long id) {
		try {
			Cart cart = cartService.GetById(id);
			ApiResponse response = ApiResponse.success("", cart);
			return ResponseEntity.ok(response);

		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}	
	
	@PostMapping("/cart")
	public ResponseEntity<ApiResponse> saveCart(@RequestBody Map<String, Long> requestBody) {
	    try {
	        Long userId = requestBody.get("user_id");
	        System.out.print("USER Id:: " + userId);
	        User user = usrService.GetById(userId);
	        System.out.print("user:: " + user);
	        if (user == null) {
	            throw new ApiRequestException("Usuario no encontrado", HttpStatus.NOT_FOUND);
	        }

	        Cart cart = new Cart();
	        cart.setUser(user);

	        Cart newCart = cartService.save(cart); 
	        ApiResponse response = ApiResponse.success("Carrito creado correctamente", newCart);
	        return ResponseEntity.ok(response);
	    } catch (Exception ex) {
	        ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    }
	}
	
	
	@PutMapping("/cart/{id}")
	public ResponseEntity<ApiResponse> updateUserId(@PathVariable long id, @RequestBody Cart cart) {
		try {
			Cart cartId = cartService.GetById(id);
			Cart  UpdatedCart = cartService.save(cartId);
			ApiResponse response = ApiResponse.success("Producto actualizada correctamente",UpdatedCart);
	        return ResponseEntity.ok(response); 
		} catch (ApiRequestException e) {
			ApiResponse errorResponse = ApiResponse.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			ApiResponse errorResponse = ApiResponse.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
}
