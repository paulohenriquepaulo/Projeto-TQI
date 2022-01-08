package br.com.tiq.repository;

import br.com.tiq.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Long countByEmail(String email);

    Long countByCpf(String cpf);

    Long countByRg(String rg);

}
