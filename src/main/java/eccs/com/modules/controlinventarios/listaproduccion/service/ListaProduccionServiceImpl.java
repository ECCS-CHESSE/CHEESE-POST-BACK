package eccs.com.modules.controlinventarios.listaproduccion.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlinventarios.listaproduccion.dto.ListaTipoSalsaRequestDto;
import eccs.com.modules.controlinventarios.listaproduccion.dto.DataIngredientesRequestDto;
import eccs.com.modules.controlinventarios.listaproduccion.query.ListaProduccionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("controlinventariosListaProduccionServiceImpl")
@RequiredArgsConstructor
public class ListaProduccionServiceImpl implements ListaProduccionService {

    private final ListaProduccionQuery listaProduccionQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getLstTipoSalsa(ListaTipoSalsaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                listaProduccionQuery.getLstTipoSalsa(request.getId_sucursal())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - LISTA PRODUCCION");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - LISTA PRODUCCION");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getLstOrillasQueso(ListaTipoSalsaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                listaProduccionQuery.getLstOrillasQueso(request.getId_sucursal())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - LISTA PRODUCCION");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - LISTA PRODUCCION");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getLstInsumos(ListaTipoSalsaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                listaProduccionQuery.getLstInsumos(request.getId_sucursal())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - LISTA PRODUCCION");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - LISTA PRODUCCION");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getLstEspecialidades() {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                listaProduccionQuery.getLstEspecialidades()
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - LISTA PRODUCCION");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - LISTA PRODUCCION");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getDataIngredientes(DataIngredientesRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                listaProduccionQuery.getDataIngredientes(request.getId_sucursal(), request.getId_especialidad())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - LISTA PRODUCCION");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - LISTA PRODUCCION");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getLstCategoriasArmaTuPizza() {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                listaProduccionQuery.getLstCategoriasArmaTuPizza()
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - LISTA PRODUCCION");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - LISTA PRODUCCION");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

}
