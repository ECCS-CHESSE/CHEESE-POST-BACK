package eccs.com.modules.controlempresa.sucursal.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlempresa.sucursal.dto.CreateSucursalRequestDto;
import eccs.com.modules.controlempresa.sucursal.query.SucursalQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SucursalServiceImpl implements SucursalService {

    private final SucursalQuery sucursalQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> createSucursal(CreateSucursalRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(sucursalQuery.createSucursal(request.getSucursal()));
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL EMPRESA - SUCURSAL");
            response.setMensaje("SUCURSAL CREADA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL EMPRESA - SUCURSAL");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> deleteSucursal(int id) {
        sucursalQuery.deleteSucursal(id);
        ResponseDto<Object> response = new ResponseDto<>();
        response.setSuccess(true);
        response.setTitulo("ECCS - CONTROL EMPRESA - SUCURSAL");
        response.setMensaje("SUCURSAL ELIMINADA DE MANERA EXITOSA");
        response.setResponse(null);
        return response;
    }
}
