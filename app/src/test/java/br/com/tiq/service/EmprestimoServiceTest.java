package br.com.tiq.service;

import br.com.tiq.exception.TQICreditoException;
import br.com.tiq.model.Cliente;
import br.com.tiq.model.Emprestimo;
import br.com.tiq.repository.ClienteRepository;
import br.com.tiq.repository.EmprestimoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmprestimoServiceTest {

    @InjectMocks
    private EmprestimoService emprestimoService;

    @Mock
    private EmprestimoRepository repository;

    @Mock
    private ClienteRepository clienteRepository;

    private Cliente cliente;
    private Emprestimo emprestimo;
    private String email;
    private String senha;
    private Long id;

    @BeforeEach
    void setUp() {
        email = "teste@gmail.com";
        senha = "123";
        id = 1l;
        emprestimo = new Emprestimo();
        emprestimo.setCliente(cliente);
        emprestimo.setValor(2.000);
        emprestimo.setId(1l);
    }

    @Test
    public void deveSubirExceptionTQICreditoExceptionQuandoDataPrimeiraParcelaEstiverNoPassado() {

        LocalDate hoje = LocalDate.now();
        LocalDate dataNoPassado = hoje.minusDays(2);

        emprestimo.setDataPrimeiraParcela(dataNoPassado);

        TQICreditoException tqiCreditoException = assertThrows(TQICreditoException.class,()
                -> emprestimoService.cadastrarEmprestimo
                (emprestimo, email, "123"));

        assertNotNull(tqiCreditoException);
        assertEquals(tqiCreditoException.getErrors().size(), 1);
        String msg = tqiCreditoException.getErrors().get("Data Primeira Parcela");
        assertNotNull(msg);
        assertEquals("A data da primeira parcela deve ser a partir da data atual", msg);

    }


    @Test
    public void deveSubirExceptionTQICreditoQuandoASenhaEstiverErrada() {

        Cliente c = new Cliente();
        c.setSenha("senhaErrada");
        Optional<Cliente> cliente = Optional.of(c);
        emprestimo.setDataPrimeiraParcela(LocalDate.now());
        when(clienteRepository.getByEmail(email)).thenReturn(cliente);
        TQICreditoException tqiCreditoException = assertThrows(TQICreditoException.class,()
                -> emprestimoService.cadastrarEmprestimo
                (emprestimo, email, senha));
        assertNotNull(tqiCreditoException);
        String msg = tqiCreditoException.getErrors().get("Senha");
        assertEquals("Senha inválida!", msg);
    }

    @Test
    public void deveCadastrarEmprestimo() {

        Cliente c = new Cliente();
        Optional<Cliente> cliente = Optional.of(c);
        c.setSenha(senha);
        when(clienteRepository.getByEmail(email)).thenReturn(cliente);
        emprestimo.setDataPrimeiraParcela(LocalDate.now());
        emprestimoService.cadastrarEmprestimo(emprestimo, email, senha);
        verify(clienteRepository, times(1)).getByEmail(email);
        verify(repository, times(1)).save(emprestimo);

    }

    @Test
    public void deveRecuperarEmprestimo() {

        Cliente c = new Cliente();
        c.setSenha(senha);
        Optional<Cliente> cliente = Optional.of(c);
        when(clienteRepository.getByEmail(email)).thenReturn(cliente);

        Optional<Emprestimo> emprestimo = Optional.of(new Emprestimo());
        when(repository.findById(id)).thenReturn(emprestimo);
        Emprestimo e = emprestimoService.recuperarEmprestimo(id, email, senha);
        verify(clienteRepository, times(1)).getByEmail(email);
        verify(repository, times(1)).findById(id);
        assertNotNull(e);

    }

    @Test
    public void deveRecuperarEmprestimos() {

        Cliente c = new Cliente();
        c.setSenha(senha);
        Optional<Cliente> cliente = Optional.of(c);
        when(clienteRepository.getByEmail(email)).thenReturn(cliente);
        emprestimoService.recuperarEmprestimos(email, senha);
        verify(clienteRepository, times(1)).getByEmail(email);

    }

    @Test
    public void deveSubirExceptionTQICreditoQuandoNaoEncontrarOEmprestimo() {

        Cliente c = new Cliente();
        c.setSenha(senha);
        Optional<Cliente> cliente = Optional.of(c);
        when(clienteRepository.getByEmail(email)).thenReturn(cliente);

        Optional<Emprestimo> emprestimo = Optional.empty();
        when(repository.findById(id)).thenReturn(emprestimo);
        TQICreditoException tqiCreditoException = assertThrows(TQICreditoException.class,()
                -> emprestimoService.recuperarEmprestimo(id, email, senha));

        assertEquals("Código de empréstimo inválido!", tqiCreditoException.getErrors().get("Emprestimo"));

    }

    @Test
    public void deveSubirExceptionTQICreditoQuandoNaoEncontrarOCliente() {

        Optional<Cliente> cliente = Optional.empty();
        when(clienteRepository.getByEmail(email)).thenReturn(cliente);
        emprestimo.setDataPrimeiraParcela(LocalDate.now());
        TQICreditoException tqiCreditoException = assertThrows(TQICreditoException.class,()
                -> emprestimoService.cadastrarEmprestimo
                (emprestimo, email, senha));
        assertNotNull(tqiCreditoException);
        String msg = tqiCreditoException.getErrors().get("Cliente");
        assertEquals("E-mail não cadastrado!", msg);
    }

    @Test
    public void deveSubirExceptionTQICreditoQuandoDataPrimeiraParcelaForAposTresMeses() {

        LocalDate hoje = LocalDate.now();
        LocalDate dataNoFuturo = hoje.plusMonths(4);

        emprestimo.setDataPrimeiraParcela(dataNoFuturo);

        TQICreditoException tqiCreditoException = assertThrows(TQICreditoException.class,()
                -> emprestimoService.cadastrarEmprestimo
                (emprestimo, "teste@gmail.com", "123"));

        assertNotNull(tqiCreditoException);
        assertEquals(tqiCreditoException.getErrors().size(), 1);
        String msg = tqiCreditoException.getErrors().get("Data Primeira Parcela");

        assertEquals("A data da primeira parcela deve ser no máximo três meses a partir da data atual: "
                .concat(hoje.plusMonths(3).toString()), msg);


    }

}
