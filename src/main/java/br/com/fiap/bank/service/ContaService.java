package br.com.fiap.bank.service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.bank.model.conta.Conta;
import br.com.fiap.bank.repository.ContaRepository;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta create(Conta conta) {
        return contaRepository.save(conta);
    }

    public ResponseEntity<Conta> showNumero(Long id) {
        return contaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Conta> show(String cpf) {
        return contaRepository.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Conta> show(Long agencia, Long numero) {
        return contaRepository.findByAgenciaAndNumero(agencia, numero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public void destroy(Long id) {
        contaRepository.findById(id)
                .ifPresent(conta -> {
                    if (conta.getAtivo()) {
                        conta.setAtivo(false);
                        contaRepository.save(conta);
                    }
                });
    }

    public Conta depositar(Long numero, BigDecimal valor){
        var conta = contaRepository.findByNumero(numero).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Conta não encontrada"));
        if (valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new ResponseStatusException(BAD_REQUEST, "Valor deve ser maior que zero");
        }
        conta.setSaldo(conta.getSaldo().add(valor));
        return contaRepository.save(conta);
    }

    public Conta saque(Long numero, BigDecimal valor) {
        var conta = contaRepository.findById(numero).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Conta não encontrada"));
        if (valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new ResponseStatusException(BAD_REQUEST, "Valor deve ser maior que zero");
        }
        if (conta.getSaldo().compareTo(valor) < 0){
            throw new ResponseStatusException(BAD_REQUEST, "Saldo insuficiente para o saque");
        }
        conta.setSaldo(conta.getSaldo().subtract(valor));
        return contaRepository.save(conta);
    }

    public Conta pix(Long numeroOrigem, Long numeroDestino, BigDecimal valor) {
        var contaOrigem = contaRepository.findById(numeroOrigem)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Conta de origem não encontrada"));
        var contaDestino = contaRepository.findById(numeroDestino)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Conta de destino não encontrada"));
        if (valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new ResponseStatusException(BAD_REQUEST, "Valor deve ser maior que zero");
        }
        if (contaOrigem.getSaldo().compareTo(valor) < 0){
            throw new ResponseStatusException(BAD_REQUEST, "Saldo insuficiente na conta de origem");
        }
        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);
        
        return contaDestino;
    }
    
    
}
