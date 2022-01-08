package br.com.tiq.controller;

import br.com.tiq.dto.ClienteDTO;
import br.com.tiq.model.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("clientes")
public class ClienteController {

    Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @PostMapping
    public ClienteDTO cadrastarCliente(@RequestBody @Valid ClienteDTO cliente) {
        logger.info("Teste", cliente);
        return null;
    }

}
