package eccs.com.modules.controlventas.folio.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlventas.folio.dto.FolioRequestDto;
import eccs.com.modules.controlventas.folio.query.FolioQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("controlinventariosFolioServiceImpl")
@RequiredArgsConstructor
public class FolioServiceImpl implements FolioService {

    private final FolioQuery folioQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> buscarFolio(FolioRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                folioQuery.buscarFolio(request.getFolio())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - FOLIO");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - FOLIO");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}
