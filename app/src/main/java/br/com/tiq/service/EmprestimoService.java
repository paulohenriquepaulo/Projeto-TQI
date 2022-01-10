package br.com.tiq.service;

import br.com.tiq.exception.TQICreditoException;
import br.com.tiq.model.Cliente;
import br.com.tiq.model.Emprestimo;
import br.com.tiq.repository.ClienteRepository;
import br.com.tiq.repository.EmprestimoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    Logger logger = LoggerFactory.getLogger(EmprestimoService.class);

    public Emprestimo cadastrarEmprestimo(Emprestimo emprestimo, String email, String senha) {
        logger.info("emprestimo:", emprestimo);
        validarEmprestimo(emprestimo);
        Cliente cliente = recuperarCliente(email);
        validarSenha(cliente, senha);
        emprestimo.setCliente(cliente);
        repository.save(emprestimo);
        return emprestimo;
    }

    private void validarSenha(Cliente cliente, String senha) {
        if (!cliente.getSenha().equals(senha)) {
            throw new TQICreditoException("Senha", "Senha inválida");
        }
    }

    private Cliente recuperarCliente(String email) {
        Optional<Cliente> cliente = clienteRepository.getByEmail(email);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new TQICreditoException("Cliente", "E-mail não cadastrado");
        }
    }

    private void validarEmprestimo(Emprestimo emprestimo) {
        TQICreditoException ex = new TQICreditoException();
        Boolean valido = Boolean.TRUE;
        LocalDate hoje = LocalDate.now();
        if (emprestimo.getDataPrimeiraParcela().isBefore(hoje)) {
            valido = Boolean.FALSE;
            ex.add("Data Primeira Parcela",
                    "A data da primeira parcela deve ser a partir da data atual");
        }
        LocalDate dataLimite = hoje.plusMonths(3l);
        if (emprestimo.getDataPrimeiraParcela().isAfter(dataLimite)) {
            valido = Boolean.FALSE;
            ex.add("Data Primeira Parcela",
                    ("A data da primeira parcela deve ser no máximo " +
                            "três meses a partir da data atual: ").concat(dataLimite.toString()));
        }
        if (!valido) {
            throw ex;
        }
    }

    public List<Emprestimo> recuperarEmprestimos(String email, String senha) {
        Cliente cliente = recuperarCliente(email);
        validarSenha(cliente, senha);
        return cliente.getEmprestimos();

    }

    public Emprestimo recuperarEmprestimo(Long id, String email, String senha) {
        Cliente cliente = recuperarCliente(email);
        validarSenha(cliente, senha);
        Optional<Emprestimo> emprestimo = repository.findById(id);
        return emprestimo.orElseThrow(() -> new TQICreditoException("Emprestimo", "Código de empréstimo inválido"));
    }
}
