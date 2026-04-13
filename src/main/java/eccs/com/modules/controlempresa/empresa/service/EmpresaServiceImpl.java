package eccs.com.modules.controlempresa.empresa.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlempresa.empresa.query.EmpresaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaQuery empresaQuery;
    // Middleware que parsea el campo JSON string retornado por la función nativa de PostgreSQL
    private final JsonParserMiddleware jsonParserMiddleware;


    @Override
    public ResponseDto<Object> getData() {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            // parseFunction extrae el JSON string del campo "fn_get_data_empresa" automáticamente
            Object result = jsonParserMiddleware.parseFunction(empresaQuery.getDataEmpresa());
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL EMPRESA - EMPRESA");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL EMPRESA - EMPRESA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}
