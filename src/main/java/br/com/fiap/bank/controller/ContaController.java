package br.com.fiap.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.bank.model.Conta;
import br.com.fiap.bank.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    @PostMapping()
    public Conta create(@RequestBody Conta conta) {
        return service.create(conta);
    }

    @GetMapping("{id}")
    public ResponseEntity<Conta> showId(@PathVariable Long id) {
        return service.showId(id);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Conta> show(@PathVariable String cpf) {
        return service.show(cpf);
    }

    @GetMapping("{agencia}/{id}")
    public ResponseEntity<Conta> show(@PathVariable Long agencia, @PathVariable Long id) {
        return service.show(agencia, id);
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        service.destroy(id);
    }
}