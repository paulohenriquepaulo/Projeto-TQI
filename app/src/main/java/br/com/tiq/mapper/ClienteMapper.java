package br.com.tiq.mapper;

import br.com.tiq.dto.ClienteDTO;
import br.com.tiq.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toModel(ClienteDTO dto);

    ClienteDTO toDto(Cliente model);

}
