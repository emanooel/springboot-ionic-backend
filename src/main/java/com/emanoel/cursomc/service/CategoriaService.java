package com.emanoel.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanoel.cursomc.domain.Categoria;
import com.emanoel.cursomc.repositories.CategoriaRepository;
import com.emanoel.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado ID:" + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	
}
