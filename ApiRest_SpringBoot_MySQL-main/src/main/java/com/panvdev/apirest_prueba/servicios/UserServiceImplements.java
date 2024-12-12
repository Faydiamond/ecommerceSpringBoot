package com.panvdev.apirest_prueba.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.excepciones.ItemAlreadyExists;
import com.panvdev.apirest_prueba.modelos.User;
import com.panvdev.apirest_prueba.repositorios.UserRepository;

@Service
public class UserServiceImplements implements IUserService {
	@Autowired
	UserRepository userService;

	@Override
	public List<User> getUsers() {
		try {
			return userService.findAll();
		} catch (DataAccessException ex) {
			throw new ApiRequestException("Error al acceder a la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public User save(User user) {
		try {
			if (userService.existsByUsername(user.getUsername())) {
				throw new ItemAlreadyExists("El nombre de usuario ya existe.");
			}
			if (userService.existsByEmail(user.getEmail())) {
				throw new ItemAlreadyExists("El correo electrónico ya existe.");
			}
			return userService.save(user);

		} catch (DataAccessException ex) {
			throw new ApiRequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public User GetById(long id) {
		Optional<User> optionalUser = userService.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			throw new ApiRequestException("Usuario no encontrado", HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public void eliminar(long id) {
		Optional<User> optionalUser = userService.findById(id);
		if (optionalUser.isPresent()) {
			 userService.deleteById(id);
		} else {
			throw new ApiRequestException("Usuario no encontrado", HttpStatus.NOT_FOUND);
		}

	}

	
	////////////////////////////////
	
    public User authenticateUser(String usernameOrEmail, String password) {
        Optional<User> userOptional = userService.findByUsername(usernameOrEmail);
        System.out.print(" userOptional >>> "+  userOptional);
        if (!userOptional.isPresent()) {
            userOptional = userService.findByEmail(usernameOrEmail);
            System.out.print(" busque por correo >>> "+  userOptional);
        }
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return user;  
            } else {
            	 System.out.print(" Contraseña incorrecta >>> "+  userOptional);
                throw new ApiRequestException("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
            }
        } else {
        	System.out.print(" Usuario no encontrado >>> "+  userOptional);
            throw new ApiRequestException("Usuario no encontrado", HttpStatus.NOT_FOUND);
       	 
        }
    }
	
	//////////////////////////////
}
