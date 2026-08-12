package eccs.com.modules.controlventas.pagos.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.pagos.dto.PagosInsertRequestDto;

public interface PagosService {

    ResponseDto<Object> getDataPago(Integer id_venta);

    ResponseDto<Object> insertPago(PagosInsertRequestDto request);

}
