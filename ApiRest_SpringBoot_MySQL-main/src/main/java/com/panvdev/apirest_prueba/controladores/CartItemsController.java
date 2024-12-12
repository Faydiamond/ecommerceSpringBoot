package com.panvdev.apirest_prueba.controladores;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.modelos.Cart;
import com.panvdev.apirest_prueba.modelos.CartItems;
import com.panvdev.apirest_prueba.modelos.Category;
import com.panvdev.apirest_prueba.modelos.Product;
import com.panvdev.apirest_prueba.modelos.User;
import com.panvdev.apirest_prueba.respuestas.ApiResponse;
import com.panvdev.apirest_prueba.servicios.CartItemServiceImplements;
import com.panvdev.apirest_prueba.servicios.CartServiceImplements;
import com.panvdev.apirest_prueba.servicios.CategoryServiceImplements;
import com.panvdev.apirest_prueba.servicios.ProductServiceImplements;
import com.panvdev.apirest_prueba.servicios.UserServiceImplements;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:2009")
public class CartItemsController {

	@Autowired
	CartItemServiceImplements cartItemService;
	@Autowired
	CartServiceImplements cartService;
	@Autowired
	ProductServiceImplements proService;

	@GetMapping("/cartsitems")
	public ResponseEntity<ApiResponse> getCartsItems() {
		try {
			List<CartItems> cartItems = cartItemService.geCartsItems();
			ApiResponse response = ApiResponse.success("Listado detalle  Carrito de compras", cartItems);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@GetMapping("/cartsitem/{id}")
	public ResponseEntity<ApiResponse> getCartsItemsId(@PathVariable long id) {
		try {
			CartItems cartitem = cartItemService.GetById(id);
			ApiResponse response = ApiResponse.success("", cartitem);
			return ResponseEntity.ok(response);

		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@PostMapping("/cartsitem")
	public ResponseEntity<ApiResponse> saveUser(@RequestBody Map<String, String> requestBody) {
		try {
			// Imprimir los datos recibidos
			System.out.println("Datos recibidos: " + requestBody.toString());

			Long cartId = Long.parseLong(requestBody.get("cart_id"));
			Cart cartFound = cartService.GetById(cartId);
			System.out.println("Cart encontrado: " + cartFound);

			if (cartFound == null) {
				throw new ApiRequestException("Cart no encontrado con el id: " + cartId, HttpStatus.NOT_FOUND);
			}

			Long productId = Long.parseLong(requestBody.get("product_id"));
			Product productFound = proService.GetById(productId);
			System.out.println("Producto encontrado: " + productFound);

			if (productFound == null) {
				throw new ApiRequestException("Producto no encontrado con el id: " + productId, HttpStatus.NOT_FOUND);
			}

			CartItems cartIt = new CartItems();
			cartIt.setCart(cartFound);
			cartIt.setProduct(productFound);
			cartIt.setQuantity(Integer.parseInt(requestBody.get("quantity")));

			System.out.println("Guardando el CartItem: " + cartIt);

			CartItems newCartItem = cartItemService.save(cartIt);
			System.out.println("CartItem guardado: " + newCartItem);

			ApiResponse response = ApiResponse.success("Producto agregado correctamente", newCartItem);
			return ResponseEntity.ok(response);

		} catch (Exception ex) {
			ex.printStackTrace();
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	
	@PutMapping("/cartsitem/{id}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable long id, @RequestBody Map<String, String> requestBody) {
	    try {
	    	
	        CartItems cartItFound = cartItemService.GetById(id);
	      
	        if (cartItFound == null) {
	            throw new ApiRequestException("Elemento del carrito no encontrado", HttpStatus.NOT_FOUND);
	        }
	        
	    	Long cartId = Long.parseLong(requestBody.get("cart_id"));
			Cart cartFound = cartService.GetById(cartId);
			System.out.println("Cart encontrado: " + cartFound);

			if (cartFound == null) {
				throw new ApiRequestException("Cart no encontrado con el id: " + cartId, HttpStatus.NOT_FOUND);
			}

			Long productId = Long.parseLong(requestBody.get("product_id"));
			Product productFound = proService.GetById(productId);
			System.out.println("Producto encontrado: " + productFound);

			if (productFound == null) {
				throw new ApiRequestException("Producto no encontrado con el id: " + productId, HttpStatus.NOT_FOUND);
			}

			cartItFound.setCart(cartFound);
			cartItFound.setProduct(productFound);
			cartItFound.setQuantity(Integer.parseInt(requestBody.get("quantity")));

			System.out.println("Guardando el CartItem: " + cartItFound);

			CartItems newCartItem = cartItemService.save(cartItFound);

			System.out.println("CartItem guardado: " + newCartItem);

			ApiResponse response = ApiResponse.success("Elemento agregado correctamente al corrito correctamente", newCartItem);
			return ResponseEntity.ok(response);
	    } catch (ApiRequestException e) {
	        ApiResponse errorResponse = ApiResponse.error(e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    } catch (Exception e) {
	        ApiResponse errorResponse = ApiResponse.error("Error inesperado: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	    }
	}

	
	@DeleteMapping("/cartsitem/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
		try {
			CartItems cartItFound = cartItemService.GetById(id);
	        if (cartItFound == null) {
	            throw new ApiRequestException("Elemento del carrito no encontrado", HttpStatus.NOT_FOUND);
	        }
			
	        cartItemService.eliminar(id);
			ApiResponse response = ApiResponse.success("Elemento del carrito eliminado correctamente",null);
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