package br.com.tiq.dto.emprestimo;

import br.com.tiq.model.SituacaoEmprestimo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class EmprestimoDTO {

    @NotNull
    private SituacaoEmprestimo situacao;

    @NotNull
    private Double valor;

    @NotNull
    private LocalDate dataPrimeiraParcela;

    @NotNull
    @Min(value = 1, message = "Deve informar no mínimo uma parcela")
    @Max(value = 60, message = "Permitido no máximo 60 parcelas")
    private Integer quantidadeParcelas;

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
}
