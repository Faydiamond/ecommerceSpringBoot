package com.panvdev.apirest_prueba.controladores;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.panvdev.apirest_prueba.modelos.Category;
import com.panvdev.apirest_prueba.modelos.Product;
import com.panvdev.apirest_prueba.modelos.User;
import com.panvdev.apirest_prueba.respuestas.ApiResponse;
import com.panvdev.apirest_prueba.servicios.CategoryServiceImplements;
import com.panvdev.apirest_prueba.servicios.ProductServiceImplements;
import com.panvdev.apirest_prueba.servicios.UserServiceImplements;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:2009")
public class ProductController {

	@Autowired
	ProductServiceImplements prooService;
	@Autowired
	UserServiceImplements userService;
	@Autowired
	CategoryServiceImplements catService;

	@GetMapping("/products")
	public ResponseEntity<ApiResponse> getProducts() {
		try {
			List<Product> products = prooService.getCategories();
			ApiResponse response = ApiResponse.success("Listado de productos", products);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<ApiResponse> getUserId(@PathVariable long id) {
		try {
			Product product = prooService.GetById(id);
			ApiResponse response = ApiResponse.success("", product);
			return ResponseEntity.ok(response);

		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@PostMapping("/product")
	public ResponseEntity<ApiResponse> saveUser(@RequestBody Map<String, String> requestBody) {
		try {
			Long catId = Long.parseLong(requestBody.get("category_id"));
			System.out.print("Category Id:: " + catId);
			Category categoryFound = catService.GetById(catId);
			System.out.print("user:: " + categoryFound);
			if (categoryFound == null) {
				throw new ApiRequestException("Categoria no encontrada", HttpStatus.NOT_FOUND);
			}
			Product product = new Product();
			product.setProductName(requestBody.get("product_name"));
			product.setDescription(requestBody.get("description"));
			product.setPrice(new BigDecimal(requestBody.get("price")));
			product.setImageUrl(requestBody.get("imageUrl"));
			product.setCategory(categoryFound);

			Product newProduct = prooService.save(product);
			ApiResponse response = ApiResponse.success("Producto creado correctamente", newProduct);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable long id, @RequestBody Map<String, String> requestBody) {
	    try {
	       
	        Long catId = Long.parseLong(requestBody.get("category_id"));
	        Category categoryFound = catService.GetById(catId);
	      
	        if (categoryFound == null) {
	            throw new ApiRequestException("Categoría no encontrada", HttpStatus.NOT_FOUND);
	        }

	        Product existingProduct = prooService.GetById(id);
	        if (existingProduct == null) {
	            throw new ApiRequestException("Producto no encontrado", HttpStatus.NOT_FOUND);
	        }

	        existingProduct.setProductName(requestBody.get("product_name"));
	        existingProduct.setDescription(requestBody.get("description"));
	        existingProduct.setPrice(new BigDecimal(requestBody.get("price")));
	        existingProduct.setImageUrl(requestBody.get("imageUrl"));
	        existingProduct.setCategory(categoryFound);

	        Product updatedProduct = prooService.save(existingProduct);
	        ApiResponse response = ApiResponse.success("Producto actualizado correctamente", updatedProduct);
	        return ResponseEntity.ok(response);

	    } catch (ApiRequestException e) {
	        ApiResponse errorResponse = ApiResponse.error(e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    } catch (Exception e) {
	        ApiResponse errorResponse = ApiResponse.error("Error inesperado: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	    }
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
		try {
			prooService.eliminar(id);
			ApiResponse response = ApiResponse.success("Producto eliminado correctamente",null);
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
