package com.emanoel.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanoel.cursomc.domain.Categoria;
import com.emanoel.cursomc.repositories.CategoriaRepositories;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepositories repositories;
	
	public Optional<Categoria> buscar(Integer id) {
		Optional<Categoria> obj = repositories.findById(id);
		return obj;
	}
	
}
