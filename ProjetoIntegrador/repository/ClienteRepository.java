package com.ProjetoIntegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoIntegrador.entities.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long>{

}