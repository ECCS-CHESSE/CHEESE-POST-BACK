package eccs.com.modules.controlinventarios.equivalencias.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlinventarios.equivalencias.dto.EquivalenciasRequestDto;
import eccs.com.modules.controlinventarios.equivalencias.query.EquivalenciasQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("controlinventariosEquivalenciasServiceImpl")
@RequiredArgsConstructor
public class EquivalenciasServiceImpl implements EquivalenciasService {

    private final EquivalenciasQuery equivalenciasQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getCatalogoEquivalencias(EquivalenciasRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                equivalenciasQuery.getCatalogoEquivalencias()
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - EQUIVALENCIAS");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - EQUIVALENCIAS");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

}
