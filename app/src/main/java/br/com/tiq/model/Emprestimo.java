package br.com.tiq.model;

import java.time.LocalDate;

public class Emprestimo {

    private SituacaoEmprestimo situacao;
    private Double valor;
    private LocalDate  dataPrimeiraParcela;
    private Integer quantidadeParcelas;
    private Integer quantidaParcelas;
    private Cliente cliente;

    public SituacaoEmprestimo getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEmprestimo situacao) {
        this.situacao = situacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataPrimeiraParcela() {
        return dataPrimeiraParcela;
    }

    public void setDataPrimeiraParcela(LocalDate dataPrimeiraParcela) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public Integer getQuantidaParcelas() {
        return quantidaParcelas;
    }

    public void setQuantidaParcelas(Integer quantidaParcelas) {
        this.quantidaParcelas = quantidaParcelas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
