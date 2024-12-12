package com.panvdev.apirest_prueba.servicios;

import java.util.List;

import com.panvdev.apirest_prueba.modelos.Disccount;

public interface IDisscountService {
	public List<Disccount> getDiscounts();
	public Disccount save(Disccount discount);
	public Disccount GetById(long id);
	public void eliminar(long id);
}
