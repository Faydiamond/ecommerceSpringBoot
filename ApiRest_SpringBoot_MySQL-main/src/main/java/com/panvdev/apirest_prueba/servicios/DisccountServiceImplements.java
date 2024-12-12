package com.panvdev.apirest_prueba.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.modelos.Disccount;
import com.panvdev.apirest_prueba.repositorios.DisscountRepository;

@Service
public class DisccountServiceImplements implements IDisscountService {
	@Autowired
	DisscountRepository discountRepo;

	@Override
	public List<Disccount> getDiscounts() {
		try {
			return discountRepo.findAll();
		} catch (DataAccessException ex) {
			throw new ApiRequestException("Error al acceder a la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Disccount save(Disccount discount) {
		try {
			return discountRepo.save(discount);
		} catch (DataAccessException ex) {
			throw new ApiRequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Disccount GetById(long id) {
		Optional<Disccount> disFound = discountRepo.findById(id);
		if (disFound.isPresent()) {
			return disFound.get();
		} else {
			throw new ApiRequestException("Descuento  no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void eliminar(long id) {
		Optional<Disccount> optionalCat = discountRepo.findById(id);
		if (optionalCat.isPresent()) {
			discountRepo.deleteById(id);
		} else {
			throw new ApiRequestException("Descuento no encontrado", HttpStatus.NOT_FOUND);
		}
	}

}
