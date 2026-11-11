package eccs.com.modules.facturacion.listados.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.facturacion.listados.dto.UsoCfdiRequestDto;
import eccs.com.modules.facturacion.listados.query.ListadosQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListadosServiceImpl implements ListadosService {

    private final ListadosQuery listadosQuery;

    @Override
    public ResponseDto<Object> getRegimenesFiscales() {
        return respuestaExitosa(listadosQuery.getRegimenesFiscales());
    }

    @Override
    public ResponseDto<Object> getUsosCfdi(UsoCfdiRequestDto request) {
        return respuestaExitosa(listadosQuery.getUsosCfdi(request.getCodigo()));
    }

    private ResponseDto<Object> respuestaExitosa(Object result) {
        ResponseDto<Object> response = new ResponseDto<>();
        response.setSuccess(true);
        response.setTitulo("ECCS - FACTURACION - LISTADOS");
        response.setMensaje("CONSULTA DE MANERA EXITOSA");
        response.setResponse(result);
        return response;
    }
}
