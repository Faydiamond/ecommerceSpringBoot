package com.panvdev.apirest_prueba.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.panvdev.apirest_prueba.modelos.User;

public interface IUserService {
	public List<User> getUsers();
	public User save(User user);
	public User GetById(long id);
	public void eliminar(long id);
	
}
