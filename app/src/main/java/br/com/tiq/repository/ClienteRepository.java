package br.com.tiq.repository;

import br.com.tiq.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Long countByEmail(String email);

    Long countByCpf(String cpf);

    Long countByRg(String rg);

    Optional<Cliente> getByEmail(String email);

}
