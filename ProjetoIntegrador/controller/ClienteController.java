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

import com.ProjetoIntegrador.entities.Cliente;
import com.ProjetoIntegrador.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes") // Changed the path to plural form
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscaClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscaClienteId(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> buscaTodosClientes() {
        List<Cliente> cliente = clienteService.buscaTodosClientes();
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/")
    public ResponseEntity<Cliente> cadastraCliente(@RequestBody @Valid Cliente cliente) {
    	Cliente clienteSalvo = clienteService.salvaCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> alteraCliente(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
    	Cliente clienteAlterado = clienteService.alterarCliente(id, cliente);
        if (clienteAlterado != null) {
            return ResponseEntity.ok(clienteAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagaCliente(@PathVariable Long id) {
        boolean apagou = clienteService.apagarCliente(id);
        if (apagou) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
