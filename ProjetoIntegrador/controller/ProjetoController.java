package com.ProjetoIntegrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoIntegrador.entities.Projeto;
import com.ProjetoIntegrador.service.ProjetoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projetos") // Changed the path to plural form
public class ProjetoController {
    private final ProjetoService projetoService;

    @Autowired
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscaProjetoPorId(@PathVariable Long id) {
    	Projeto projeto = projetoService.buscaProjetoId(id);
        if (projeto != null) {
            return ResponseEntity.ok(projeto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Projeto>> buscaTodosProjetos() {
        List<Projeto> projeto = projetoService.buscaTodosProjetos();
        return ResponseEntity.ok(projeto);
    }

    @PostMapping("/")
    public ResponseEntity<Projeto> cadastraProjeto(@RequestBody @Valid Projeto projeto) {
    	Projeto projetoSalvo = projetoService.salvaProjeto(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> alteraProjeto(@PathVariable Long id, @RequestBody @Valid Projeto projeto) {
    	Projeto projetoAlterado = projetoService.alterarProjeto(id, projeto);
        if (projetoAlterado != null) {
            return ResponseEntity.ok(projetoAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagaProjeto(@PathVariable Long id) {
        boolean apagou = projetoService.apagarProjeto(id);
        if (apagou) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
