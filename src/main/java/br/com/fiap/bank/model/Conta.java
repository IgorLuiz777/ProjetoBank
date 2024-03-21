package br.com.fiap.bank.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.com.fiap.validation.TipoConta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class Conta {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true)
    private Long numero;

    private String agencia;

    @NotBlank
    private String nome;

    @CPF
    private String cpf;

    @PastOrPresent
    private LocalDate dataAbertura;

    @Positive
    private BigDecimal saldoInicial;
    
    private Boolean ativo = true;

    @TipoConta
    private String tipo; // corrente, poupança ou salário

}
