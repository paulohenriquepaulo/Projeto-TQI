package br.com.tiq.dto.emprestimo;

import java.time.LocalDate;

public class EmprestimoDetailDTO {

    private Long codigo;

    private Double valorEmprestimo;

    private Integer quantidadeParcelas;

    private String emailCliente;

    private Double rendaCliente;

    private LocalDate dataPrimeiraParcela;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Double getValorEmprestimo() {
        return valorEmprestimo;
    }

    public void setValorEmprestimo(Double valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public Double getRendaCliente() {
        return rendaCliente;
    }

    public void setRendaCliente(Double rendaCliente) {
        this.rendaCliente = rendaCliente;
    }

    public LocalDate getDataPrimeiraParcela() {
        return dataPrimeiraParcela;
    }

    public void setDataPrimeiraParcela(LocalDate dataPrimeiraParcela) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
    }
}
