package eccs.com.modules.controlventas.clientes.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlventas.clientes.dto.ClientesBuscarRequestDto;
import eccs.com.modules.controlventas.clientes.dto.ClientesCrearRequestDto;
import eccs.com.modules.controlventas.clientes.query.ClientesQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientesServiceImpl implements ClientesService {

    private final ClientesQuery clientesQuery;
    // Middleware que parsea el campo JSON string retornado por la función nativa de PostgreSQL
    private final JsonParserMiddleware jsonParserMiddleware;

    
    @Override
    public ResponseDto<Object> getCrearCliente(ClientesCrearRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                clientesQuery.getCrearCliente(
                    request.getId(),
                    request.getId_sucursal(),
                    request.getId_empleado(),
                    request.getNombre(),
                    request.getApellidoPaterno(),
                    request.getApellidoMaterno(),
                    request.getTelefono()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - CLIENTES - CREAR");
            response.setMensaje("CLIENTE CREADO DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - CLIENTES - CREAR");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getBusquedaCliente(ClientesBuscarRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                clientesQuery.getBusquedaCliente(
                    request.getDescripcion()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - CLIENTES - BUSCAR");
            response.setMensaje("CLIENTE ENCONTRADO DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - CLIENTES - BUSCAR");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

}