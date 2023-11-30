package com.ProjetoIntegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoIntegrador.entities.Projeto;

public interface ProjetoRepository extends JpaRepository <Projeto, Long>{

}