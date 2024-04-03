package br.com.fiap.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.bank.model.conta.Conta;
import br.com.fiap.bank.model.movimentacao.Movimentacao;
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

    @GetMapping("{numero}")
    public ResponseEntity<Conta> showNumero(@PathVariable Long numero) {
        return service.showNumero(numero);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Conta> show(@PathVariable String cpf) {
        return service.show(cpf);
    }

    @GetMapping("{agencia}/{numero}")
    public ResponseEntity<Conta> show(@PathVariable Long agencia, @PathVariable Long numero) {
        return service.show(agencia, numero);
    }

    @DeleteMapping("{numero}")
    public void destroy(@PathVariable Long numero) {
        service.destroy(numero);
    }

    @PostMapping("{numero}/deposito")
    public Conta depositar(@PathVariable Long numero, @RequestBody Movimentacao movimentacao){
        return service.depositar(numero, movimentacao.valor());
    }

    @PostMapping("{numero}/saque")
    public Conta sacar(@PathVariable Long numero, @RequestBody Movimentacao movimentacao){
        return service.saque(numero, movimentacao.valor());
    }

    @PostMapping("{numeroOrigem}/pix/{numeroDestino}")
    public ResponseEntity<Conta> pix(@PathVariable Long numeroOrigem, @PathVariable Long numeroDestino, @RequestBody Movimentacao movimentacao){
        Conta contaAtualizada = service.pix(numeroOrigem, numeroDestino, movimentacao.valor());
        return ResponseEntity.ok(contaAtualizada);
}


}
