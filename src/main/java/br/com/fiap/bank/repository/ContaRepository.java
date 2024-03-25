package br.com.fiap.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.bank.model.conta.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

    Optional<Conta> findByCpf(String cpf);

    Optional<Conta> findById(Long id);

    Optional<Conta> findByAgenciaAndId(Long agencia, Long id);

    
}
