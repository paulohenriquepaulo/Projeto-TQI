package br.com.tiq.service;

import br.com.tiq.exception.TQICreditoException;
import br.com.tiq.model.Cliente;
import br.com.tiq.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ClienteServiceTest {

    @MockBean
    private ClienteRepository repository;


    @InjectMocks
    Cliente cliente;

    @Autowired
    private ClienteService service;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setEmail("teste@gmail.com");
        cliente.setSenha("123");
        Mockito.doReturn(cliente).when(repository).save(cliente);
    }

    @Test
    void cadastrarCliente() {
        service.cadastrarCliente(cliente);
        Assertions.assertEquals("teste@gmail.com", cliente.getEmail() );
        Assertions.assertEquals("123", cliente.getSenha());
        verify(repository, times(1)).save(cliente);
    }

}