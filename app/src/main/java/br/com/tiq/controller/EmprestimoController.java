package br.com.tiq.controller;

import br.com.tiq.dto.emprestimo.EmprestimoDTO;
import br.com.tiq.dto.emprestimo.EmprestimoDetailDTO;
import br.com.tiq.dto.emprestimo.EmprestimoListDTO;
import br.com.tiq.exception.TQICreditoException;
import br.com.tiq.mapper.EmprestimoMapper;
import br.com.tiq.model.Emprestimo;
import br.com.tiq.service.EmprestimoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("emprestimos")
public class EmprestimoController {

    Logger logger = LoggerFactory.getLogger(EmprestimoController.class);

    @Autowired
    private EmprestimoMapper mapper;

    @Autowired
    private EmprestimoService service;

    @PostMapping
    public EmprestimoDTO cadrastarEmprestimo(
            @RequestBody @Valid EmprestimoDTO emprestimo,
            @Valid @Email @RequestHeader(name = "email", required = false) String email,
            @RequestHeader(name = "senha", required = false) String senha) {
        logger.info("Teste", emprestimo);
        validarLogin(email, senha);
        Emprestimo emprestimoNovo = mapper.toModel(emprestimo);
        Emprestimo emprestimoCadastrado = service.cadastrarEmprestimo(emprestimoNovo, email, senha);
        return mapper.toDto(emprestimoCadastrado);
    }

    @GetMapping
    public List<EmprestimoListDTO> getEmprestimos(
            @Valid @Email @RequestHeader(name = "email", required = false) String email,
            @RequestHeader(name = "senha", required = false) String senha
    ) {
        validarLogin(email, senha);
        List<Emprestimo> emprestimos = service.recuperarEmprestimos(email, senha);
        return emprestimos.stream()
                .map(e -> mapper.toModelList(e))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @GetMapping(value = "/{id}")
    public EmprestimoDetailDTO detalharEmprestimo(
            @PathVariable @NotNull Long id,
            @Valid @Email @RequestHeader(name = "email", required = false) String email,
            @RequestHeader(name = "senha", required = false) String senha
    ) {
        validarLogin(email, senha);
        Emprestimo emprestimo = service.recuperarEmprestimo(id, email, senha);
        return mapper.toModelDetail(emprestimo);
    }

    private void validarLogin(String email, String senha) {
        TQICreditoException ex = new TQICreditoException();
        Boolean valido = Boolean.TRUE;
        if (email == null || email.equals("")) {
            valido = Boolean.FALSE;
            ex.add("E-Mail", "Deve informar o e-mail de login no header");
        }
        if (senha == null || senha.equals("")) {
            valido = Boolean.FALSE;
            ex.add("Senha", "Deve informar a senha de login no header");
        }
        if (!valido) {
            throw ex;
        }
    }


}
