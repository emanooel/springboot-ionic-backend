package com.emanoel.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.emanoel.cursomc.domain.Categoria;
import com.emanoel.cursomc.repositories.CategoriaRepository;
import com.emanoel.cursomc.service.exceptions.DataIntegrityException;
import com.emanoel.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	/*Buscar*/
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado ID:" + id + ", Tipo: " + Categoria.class.getName()));
	}
	/*Inserir*/
	public Categoria inserir(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	/*Atualizar*/
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repository.save(obj);
	}
	/*Excluir*/
	public void excluir(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	/*Listar todos*/
	public List<Categoria> findAll() {
		
		return repository.findAll();
	}
	
	/*Paginaçao*/
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
}
