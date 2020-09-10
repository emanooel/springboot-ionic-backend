package com.emanoel.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanoel.cursomc.domain.Pedido;
import com.emanoel.cursomc.repositories.PedidoRepository;
import com.emanoel.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado ID:" + id + ", Tipo: " + Pedido.class.getName()));
	}
}
