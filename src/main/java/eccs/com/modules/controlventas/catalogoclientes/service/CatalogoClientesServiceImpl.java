package eccs.com.modules.controlventas.catalogoclientes.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlventas.catalogoclientes.dto.CatalogoClientesRequestDto;
import eccs.com.modules.controlventas.catalogoclientes.query.CatalogoClientesQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogoClientesServiceImpl implements CatalogoClientesService {

    private final CatalogoClientesQuery catalogoClientesQuery;
    // Middleware que parsea el campo JSON string retornado por la función nativa de PostgreSQL
    private final JsonParserMiddleware jsonParserMiddleware;

    /**
     * Obtiene el catálogo de clientes ejecutando la función PostgreSQL
     * "controlventas".fn_get_catalogo_clientes() y parsea el campo JSON
     * cuyo nombre es igual al de la función usando parseFunction.
     */
    @Override
    public ResponseDto<Object> getCatalogoClientes(CatalogoClientesRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(catalogoClientesQuery.getCatalogoClientes(request));
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - CATALOGO CLIENTES");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - CATALOGO CLIENTES");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}
