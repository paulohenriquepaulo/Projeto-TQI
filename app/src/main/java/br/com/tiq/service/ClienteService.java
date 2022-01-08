package br.com.tiq.service;

import br.com.tiq.controller.ClienteController;
import br.com.tiq.exception.TQICreditoException;
import br.com.tiq.model.Cliente;
import br.com.tiq.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    Logger logger = LoggerFactory.getLogger(ClienteService.class);

    public Cliente cadastrarCliente(Cliente cliente) {
        logger.info("cliente:", cliente);
        validarCliente(cliente);
        repository.save(cliente);
        return cliente;
    }

    private void validarCliente(Cliente cliente) {
        Boolean valido = Boolean.TRUE;
        TQICreditoException ex = new TQICreditoException();
        Long qtd = repository.countByEmail(cliente.getEmail());
        if (qtd >= 1) {
            valido = Boolean.FALSE;
            ex.add("E-Mail", "Este e-mail já está sendo utilizado");
        }
        qtd = repository.countByCpf(cliente.getCpf());
        if (qtd >= 1) {
            valido = Boolean.FALSE;
            ex.add("CPF", "Este CPF já está sendo utilizado");
        }
        qtd = repository.countByRg(cliente.getRg());
        if (qtd >= 1) {
            valido = Boolean.FALSE;
            ex.add("RG", "Este RG já está sendo utilizado");
        }
        if (!valido) {
            throw ex;
        }
    }



}
