package br.com.fiap.bank.model.movimentacao;

import java.math.BigDecimal;

public record Movimentacao(BigDecimal valor, Long idConta) {
    
}
