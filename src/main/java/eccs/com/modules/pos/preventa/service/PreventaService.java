package eccs.com.modules.pos.preventa.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.pos.preventa.dto.EliminarIngredientePreventaRequestDto;
import eccs.com.modules.pos.preventa.dto.IngredientesProduccionRequestDto;
import eccs.com.modules.pos.preventa.dto.PreventaRequestDto;
import eccs.com.modules.pos.preventa.dto.UpdateProductoImagenRequestDto;
import eccs.com.modules.pos.preventa.dto.UpdateTipoVentaRequestDto;

public interface PreventaService {
    public ResponseDto<Object> getPreciosPizzas(PreventaRequestDto request);
    public ResponseDto<Object> getDataIngredientesProduccionInfo(IngredientesProduccionRequestDto request);
    public ResponseDto<Object> eliminarIngredientePreventa(EliminarIngredientePreventaRequestDto request);
    public ResponseDto<Object> updateTipoVenta(UpdateTipoVentaRequestDto request);
    public ResponseDto<Object> updateProductoImagen(UpdateProductoImagenRequestDto request);
}
