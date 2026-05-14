package eccs.com.modules.controlventas.catalogocortes.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlventas.catalogocortes.dto.CatalogoCortesRequestDto;
import eccs.com.modules.controlventas.catalogocortes.query.CatalogoCortesQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogoCortesServiceImpl implements CatalogoCortesService {

    private final CatalogoCortesQuery catalogoCortesQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getCatalogoCortes(CatalogoCortesRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                catalogoCortesQuery.getCatalogoCortes(request.getId_sucursal())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - CATALOGO CORTES");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - CATALOGO CORTES");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}
