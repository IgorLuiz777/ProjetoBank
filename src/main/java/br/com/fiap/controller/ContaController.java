package br.com.fiap.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.bank.model.Conta;
import br.com.fiap.repository.ContaRepository;


@RestController
@RequestMapping("/conta")
public class ContaController {
    
    @Autowired
    ContaRepository contaRepository;

    @PostMapping()
    @ResponseStatus(CREATED)
    public Conta create(@RequestBody Conta conta) {

        return contaRepository.save(conta);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Conta> showId(@PathVariable Long id) {

        return contaRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Conta> show(@PathVariable String cpf){
 
        return contaRepository
        .findByCpf(cpf)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("{id}/{agencia}")
    public ResponseEntity<Conta> show(@PathVariable Long id, String agencia){
 
        return contaRepository
        .findByIdAgencia(id, agencia)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        contaRepository.findById(id)
            .ifPresent(conta -> {
            if (conta.getAtivo()) {
                conta.setAtivo(false);
                contaRepository.save(conta);
            }
            });
  }
    




}
