package br.com.tiq.dto.cliente;

import br.com.tiq.dto.endereco.EnderecoDTO;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClienteDTO {

    @NotEmpty(message = "Nome obrigatório")
    private String nome;

    @Email
    @NotEmpty(message = "E-mail obrigatório")
    private String email;

    @CPF
    @NotEmpty(message = "CPF obrigatório")
    private String cpf;

    @NotEmpty(message = "RG obrigatório")
    private String rg;

    @Valid
    @NotNull(message = "Endereço obrigatório")
    private EnderecoDTO endereco;

    @NotNull(message = "Renda obrigatória")
    private Double renda;

    @NotEmpty(message = "Senha obrigatória")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
