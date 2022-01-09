package br.com.tiq.repository;

import br.com.tiq.dto.emprestimo.EmprestimoDTO;
import br.com.tiq.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
