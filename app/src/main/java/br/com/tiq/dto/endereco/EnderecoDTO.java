package br.com.tiq.dto.endereco;

import br.com.tiq.model.Estado;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class EnderecoDTO {

    @NotEmpty(message = "Lougradouro obrigatório.")
    private String logradouro;

    @NotNull(message = "Estado obrigatório")
    private Estado estado;

    @NotEmpty(message = "Cidade obrigatória.")
    private String cidade;

    @NotNull(message = "Numero obrigatório")
    private Integer numero;

    private String complemento;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
