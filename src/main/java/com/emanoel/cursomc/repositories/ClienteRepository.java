package com.emanoel.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanoel.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
}
