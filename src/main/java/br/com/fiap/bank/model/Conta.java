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
import jakarta.validation.constraints.NotNull;
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

    @Positive
    private Long agencia;

    @NotBlank(message = "O nome deve ser obrigatório!!")
    private String nome;

    @CPF(message = "CPF, inválido!")
    private String cpf;

    @PastOrPresent(message = "Digite uma data válida, não pode ser uma data futura")
    private LocalDate dataAbertura;

    @Positive(message = "São não pode ser negativo")
    private BigDecimal saldo;
    
    private Boolean ativo = true;

    @TipoConta
    private String tipo; // corrente, poupança ou salário

}
