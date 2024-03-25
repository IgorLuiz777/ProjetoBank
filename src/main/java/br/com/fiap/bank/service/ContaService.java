package br.com.fiap.bank.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fiap.bank.model.Conta;
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

    public ResponseEntity<Conta> showId(Long id) {
        return contaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Conta> show(String cpf) {
        return contaRepository.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Conta> show(Long agencia, Long id) {
        return contaRepository.findByAgenciaAndId(agencia, id)
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
}
