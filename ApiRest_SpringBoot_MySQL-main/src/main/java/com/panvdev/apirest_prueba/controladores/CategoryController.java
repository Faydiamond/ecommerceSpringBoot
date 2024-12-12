package com.panvdev.apirest_prueba.controladores;

import java.util.HashMap;
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

import com.panvdev.apirest_prueba.servicios.CategoryServiceImplements;
import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.modelos.Category;
import com.panvdev.apirest_prueba.modelos.User;
import com.panvdev.apirest_prueba.respuestas.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:2009")
public class CategoryController {
	@Autowired
	CategoryServiceImplements catService;
	@GetMapping("/categories")
    public ResponseEntity<ApiResponse> getCategories() {
        try {
            List<Category> categories = catService.getCategories();
            ApiResponse response = ApiResponse.success("Listado de categorías", categories);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
	
	@GetMapping("/category/{id}")
	public ResponseEntity<ApiResponse> getUserId(@PathVariable long id) {
		try {
			Category category = catService.GetById(id);
			ApiResponse response = ApiResponse.success("", category);
			return ResponseEntity.ok(response);

		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}	
	
	@PostMapping("/category")
	public ResponseEntity<ApiResponse> saveUser(@RequestBody Category cat) {
		try {
			Category newCategory = catService.save(cat);
			ApiResponse response = ApiResponse.success("Categoria creada correctamente", newCategory);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	@PutMapping("/category/{id}")
	public ResponseEntity<ApiResponse> updateUserId(@PathVariable long id, @RequestBody Category cat) {
		try {
			Category categoryId = catService.GetById(id);
			categoryId.setCategoty(cat.getCategoty());
			Category UpdatedCat = catService.save(categoryId);
			ApiResponse response = ApiResponse.success("Categoria actualizada correctamente",UpdatedCat);
	        return ResponseEntity.ok(response); 
		} catch (ApiRequestException e) {
			ApiResponse errorResponse = ApiResponse.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			ApiResponse errorResponse = ApiResponse.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}	

	@DeleteMapping("/category/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
		try {
			catService.eliminar(id);
			ApiResponse response = ApiResponse.success("Categoria eliminada correctamente",null);
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

