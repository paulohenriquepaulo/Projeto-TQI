package br.com.tiq.mapper;

import br.com.tiq.dto.emprestimo.EmprestimoDTO;
import br.com.tiq.dto.emprestimo.EmprestimoDetailDTO;
import br.com.tiq.dto.emprestimo.EmprestimoListDTO;
import br.com.tiq.model.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {

    Emprestimo toModel(EmprestimoDTO dto);

    EmprestimoDTO toDto(Emprestimo model);

    @Mapping(target = "codigo", source = "id")
    EmprestimoListDTO toModelList(Emprestimo model);

    @Mapping(target = "codigo", source = "id")
    @Mapping(target = "emailCliente", source = "cliente.email")
    @Mapping(target = "rendaCliente", source = "cliente.renda")
    @Mapping(target = "valorEmprestimo", source = "valor")
    EmprestimoDetailDTO toModelDetail(Emprestimo emprestimo);
}
