package br.com.fiap.bank.model.conta;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosListagemConta(Long numero, Long agencia, String nome, String cpf, LocalDate dataAbertura, BigDecimal saldo, String tipo) {
 
    public DadosListagemConta(Conta conta) {

        this(conta.getNumero(), conta.getAgencia(), conta.getNome(), conta.getCpf(), conta.getDataAbertura(), conta.getSaldo(), conta.getTipo());
    }

}
