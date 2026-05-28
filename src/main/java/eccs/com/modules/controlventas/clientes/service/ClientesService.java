package eccs.com.modules.controlventas.clientes.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.clientes.dto.ClientesCrearRequestDto;

public interface ClientesService {
    public ResponseDto<Object> getCrearCliente(ClientesCrearRequestDto request);
}
