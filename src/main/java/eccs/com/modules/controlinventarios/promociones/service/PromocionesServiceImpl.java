package eccs.com.modules.controlinventarios.promociones.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.promociones.dto.ActualizarPromocionRequestDto;
import eccs.com.modules.controlinventarios.promociones.dto.AgregarPromocionRequestDto;
import eccs.com.modules.controlinventarios.promociones.entity.PromocionesEntity;
import eccs.com.modules.controlinventarios.promociones.query.PromocionesQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromocionesServiceImpl implements PromocionesService {

    private final PromocionesQuery promocionesQuery;

    @Override
    public ResponseDto<Object> data() {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PROMOCIONES");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(promocionesQuery.findAll());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PROMOCIONES");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> agregar(AgregarPromocionRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            PromocionesEntity entity = new PromocionesEntity();
            entity.setId_eccs_producto(request.getId_eccs_producto());
            entity.setCantidad_producto(request.getCantidad_producto());
            entity.setPrecio_producto(request.getPrecio_producto());
            entity.setCosto_producto(request.getCosto_producto());
            entity.setId_eccs_equivalencia_producto(request.getId_eccs_equivalencia_producto());
            entity.setId_eccs_clasificacion(request.getId_eccs_clasificacion());
            entity.setId_eccs_sucursal(request.getId_eccs_sucursal());
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PROMOCIONES");
            response.setMensaje("PROMOCION AGREGADA DE MANERA EXITOSA");
            response.setResponse(promocionesQuery.save(entity));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PROMOCIONES");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> actualizar(ActualizarPromocionRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            PromocionesEntity entity = promocionesQuery.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Promocion no encontrada"));
            entity.setId_eccs_producto(request.getId_eccs_producto());
            entity.setCantidad_producto(request.getCantidad_producto());
            entity.setPrecio_producto(request.getPrecio_producto());
            entity.setCosto_producto(request.getCosto_producto());
            entity.setId_eccs_equivalencia_producto(request.getId_eccs_equivalencia_producto());
            entity.setId_eccs_clasificacion(request.getId_eccs_clasificacion());
            entity.setId_eccs_sucursal(request.getId_eccs_sucursal());
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PROMOCIONES");
            response.setMensaje("PROMOCION ACTUALIZADA DE MANERA EXITOSA");
            response.setResponse(promocionesQuery.save(entity));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PROMOCIONES");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> eliminar(Long id) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            promocionesQuery.deleteById(id);
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PROMOCIONES");
            response.setMensaje("PROMOCION ELIMINADA DE MANERA EXITOSA");
            response.setResponse(null);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PROMOCIONES");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

}
