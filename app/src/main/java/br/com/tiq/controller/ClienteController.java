package br.com.tiq.controller;

import br.com.tiq.dto.cliente.ClienteDTO;
import br.com.tiq.mapper.ClienteMapper;
import br.com.tiq.model.Cliente;
import br.com.tiq.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("clientes")
public class ClienteController  {

    Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteMapper mapper;

    @PostMapping
    public ClienteDTO cadrastarCliente(@RequestBody @Valid ClienteDTO cliente) {
        logger.info("Teste", cliente);
        Cliente clienteNovo = mapper.toModel(cliente);
        Cliente clienteCadastrado = service.cadastrarCliente(clienteNovo);
        return mapper.toDto(clienteCadastrado);
    }

    public ClienteService getService() {
        return service;
    }

    public void setService(ClienteService service) {
        this.service = service;
    }
}
