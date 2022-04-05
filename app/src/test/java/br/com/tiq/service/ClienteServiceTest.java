package br.com.tiq.service;

import br.com.tiq.exception.TQICreditoException;
import br.com.tiq.model.Cliente;
import br.com.tiq.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private  ClienteRepository repository;


    private Cliente cliente;
    private String email;
    private String senha;
    private Long id;

    @BeforeEach
    void setUp() {
        email = "teste@gmail.com";
        senha = "123";
        id = 1l;
    }

    @Test
    public void deveCadastrarCliente() {

        Cliente c = new Cliente();
        Optional<Cliente> cliente = Optional.of(c);
        c.setSenha(senha);
        clienteService.cadastrarCliente(c);
        verify(repository, times(1)).save(c);

    }

    @Test
    public void deveSubirExceptionTQICreditoQuandoEmailEstiverSendoUtilizado() {

        Cliente c = new Cliente();
        c.setEmail(email);
        Optional<Cliente> cliente = Optional.of(c);
        when(repository.countByEmail(email)).thenReturn(1l);

        TQICreditoException tqiCreditoException = assertThrows(TQICreditoException.class,()
                -> clienteService.cadastrarCliente(c));

        assertNotNull(tqiCreditoException);
        assertEquals("Este e-mail já está sendo utilizado",
                tqiCreditoException.getErrors().get("E-Mail") );
    }

    @Test
    public void deveSubirExceptionTQICreditoQuandoCpfEstiverSendoUtilizado() {

        Cliente c = new Cliente();
        c.setCpf("18162888535");
        Optional<Cliente> cliente = Optional.of(c);
        when(repository.countByCpf("18162888535")).thenReturn(1l);

        TQICreditoException tqiCreditoException = assertThrows(TQICreditoException.class,()
                -> clienteService.cadastrarCliente(c));

        assertNotNull(tqiCreditoException);
        assertEquals("Este CPF já está sendo utilizado",
                tqiCreditoException.getErrors().get("CPF") );
    }

    @Test
    public void deveSubirExceptionTQICreditoQuandoRGEstiverSendoUtilizado() {

        Cliente c = new Cliente();
        c.setRg("RG");
        Optional<Cliente> cliente = Optional.of(c);
        when(repository.countByRg("RG")).thenReturn(1l);

        TQICreditoException tqiCreditoException = assertThrows(TQICreditoException.class,()
                -> clienteService.cadastrarCliente(c));

        assertNotNull(tqiCreditoException);
        assertEquals("Este RG já está sendo utilizado",
                tqiCreditoException.getErrors().get("RG") );
    }


}
