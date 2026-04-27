package eccs.com.modules.controlempresa.catalogosucursales.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlempresa.catalogosucursales.dto.CatalogoSucursalesRequestDto;
import eccs.com.modules.controlempresa.catalogosucursales.query.CatalogoSucursalesQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogoSucursalesServiceImpl implements CatalogoSucursalesService {

    private final CatalogoSucursalesQuery catalogoSucursalesQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getCatalogoSucursales(CatalogoSucursalesRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(catalogoSucursalesQuery.getCatalogoSucursales(request.getId_estatus()));
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL EMPRESA - CATALOGO SUCURSALES");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL EMPRESA - CATALOGO SUCURSALES");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}
