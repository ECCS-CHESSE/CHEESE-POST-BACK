package eccs.com.modules.pos.catalogoplataformas.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.pos.catalogoplataformas.dto.CatalogoPlataformasRequestDto;
import eccs.com.modules.pos.catalogoplataformas.query.CatalogoPlataformasQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogoPlataformasServiceImpl implements CatalogoPlataformasService {

    private final CatalogoPlataformasQuery cataloPlataformasQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getCatalogoPlataformas(CatalogoPlataformasRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                cataloPlataformasQuery.getCatalogoPlataformas(request.getId_sucursal())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - POS - CATALOGO PLATAFORMAS");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - POS - CATALOGO PLATAFORMAS");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}
