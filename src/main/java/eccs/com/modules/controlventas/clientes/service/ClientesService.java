package eccs.com.modules.controlventas.clientes.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.clientes.dto.ClientesBuscarRequestDto;
import eccs.com.modules.controlventas.clientes.dto.ClientesCrearRequestDto;
import eccs.com.modules.controlventas.clientes.dto.ClientesCrearDomicilioRequestDto;
import eccs.com.modules.controlventas.clientes.dto.ClientesActualizarDomicilioRequestDto;
import eccs.com.modules.controlventas.clientes.dto.ClientesDomicilioRequestDto;
import eccs.com.modules.controlventas.clientes.dto.ClientesAgregarDomicilioVentaRequestDto;

public interface ClientesService {
    public ResponseDto<Object> getCrearCliente(ClientesCrearRequestDto request);
    public ResponseDto<Object> getBusquedaCliente(ClientesBuscarRequestDto request);
    public ResponseDto<Object> getDomicilioCliente(ClientesDomicilioRequestDto request);
    public ResponseDto<Object> getCrearDomicilioCliente(ClientesCrearDomicilioRequestDto request);
    public ResponseDto<Object> getActualizarDomicilioCliente(ClientesActualizarDomicilioRequestDto request);
    public ResponseDto<Object> getAgregarDomicilioVenta(ClientesAgregarDomicilioVentaRequestDto request);
}
