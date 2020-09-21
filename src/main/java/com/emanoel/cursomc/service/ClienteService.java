package com.emanoel.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.emanoel.cursomc.domain.Cliente;
import com.emanoel.cursomc.dto.ClienteDTO;
import com.emanoel.cursomc.repositories.ClienteRepository;
import com.emanoel.cursomc.service.exceptions.DataIntegrityException;
import com.emanoel.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado ID:" + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	/*Atualizar*/
	public Cliente update(Cliente obj) {
		Cliente newObj = buscar(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	/*Excluir*/
	public void excluir(Integer id) {
		buscar(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente que possui pedidos");
		}
	}
	/*Listar todos*/
	public List<Cliente> findAll() {
		
		return repository.findAll();
	}
	
	/*Paginaçao*/
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	 public Cliente fromDTO (ClienteDTO objDto) {
		 return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	 }
	 
	 private void updateData(Cliente newObj, Cliente obj) {
		 newObj.setNome(obj.getNome());
		 newObj.setEmail(obj.getEmail());
	 }
}
