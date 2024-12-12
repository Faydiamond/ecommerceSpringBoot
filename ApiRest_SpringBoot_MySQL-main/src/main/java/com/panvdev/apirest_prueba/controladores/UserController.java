package com.panvdev.apirest_prueba.controladores;

import java.util.HashMap;
import com.panvdev.apirest_prueba.respuestas.*;
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

import com.panvdev.apirest_prueba.modelos.Category;
import com.panvdev.apirest_prueba.modelos.LoginRequest;
import com.panvdev.apirest_prueba.modelos.User;
import com.panvdev.apirest_prueba.servicios.UserServiceImplements;
import com.panvdev.apirest_prueba.excepciones.*;
import com.panvdev.apirest_prueba.respuestas.*;

import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.http.HttpMethod; 

@RestController
@RequestMapping("/api/v1")

public class UserController {
	@Autowired
	UserServiceImplements userService;

	@GetMapping("/users")
	public ResponseEntity<ApiResponse> getUsers() {
		try {

			List<User> users = userService.getUsers();
			ApiResponse response = ApiResponse.success("Listado de usuarios", users);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	
	@GetMapping("/user/{id}")
	public ResponseEntity<ApiResponse> getUserId(@PathVariable long id) {
		try {
			User user = userService.GetById(id);
			ApiResponse response = ApiResponse.success("Usuario por id", user);
			return ResponseEntity.ok(response);

		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@PostMapping("/user")
	public ResponseEntity<ApiResponse> saveUser(@RequestBody User user) {
		try {
			User newUser = userService.save(user);
			ApiResponse response = ApiResponse.success("Usuario creado correctamente", newUser);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<ApiResponse> updateUserId(@PathVariable long id, @RequestBody User user) {
		try {
			
			User userId = userService.GetById(id);
			userId.setUsername(user.getUsername());
			userId.setEmail(user.getEmail());
			userId.setPassword(user.getPassword());
			userId.setFirst_name(user.getFirst_name());
			userId.setLast_name(user.getLast_name());
			userId.setAddress(user.getAddress());
			userId.setCity(user.getCity());
			userId.setPostal_code(user.getPostal_code());
			userId.setCountry(user.getCountry());
			User UpdatedUser = userService.save(userId);
			ApiResponse response = ApiResponse.success("Usuario actualizado correctamente",UpdatedUser);
	        return ResponseEntity.ok(response); 
		} catch (ApiRequestException e) {
			ApiResponse errorResponse = ApiResponse.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			ApiResponse errorResponse = ApiResponse.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
		try {
			userService.eliminar(id);
			ApiResponse response = ApiResponse.success("Usuario eliminado correctamente",null);
	        return ResponseEntity.ok(response); 
		} catch (ApiRequestException ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			ApiResponse errorResponse = ApiResponse.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
        	System.out.println("Email recibido: " + loginRequest.getUsernameOrEmail() + "password " + loginRequest.getPassword()); 
            User user = userService.authenticateUser(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
            ApiResponse response = ApiResponse.success("Usuario autenticado correctamente", user);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

}
