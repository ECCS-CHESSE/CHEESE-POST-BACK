package eccs.com.modules.controlinventarios.especialidades.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.especialidades.dto.ActualizarEspecialidadRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.AgregarEspecialidadRequestDto;
import eccs.com.modules.controlinventarios.especialidades.entity.EspecialidadesEntity;
import eccs.com.modules.controlinventarios.especialidades.query.EspecialidadesQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EspecialidadesServiceImpl implements EspecialidadesService {

    private final EspecialidadesQuery especialidadesQuery;

    @Override
    public ResponseDto<Object> data() {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ESPECIALIDADES");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(especialidadesQuery.findAll());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ESPECIALIDADES");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> agregar(AgregarEspecialidadRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            EspecialidadesEntity entity = new EspecialidadesEntity();
            entity.setId_eccs_producto(request.getId_eccs_producto());
            entity.setCantidad_producto(request.getCantidad_producto());
            entity.setPrecio_producto(request.getPrecio_producto());
            entity.setCosto_producto(request.getCosto_producto());
            entity.setId_eccs_equivalencia_producto(request.getId_eccs_equivalencia_producto());
            entity.setId_eccs_clasificacion(request.getId_eccs_clasificacion());
            entity.setId_eccs_sucursal(request.getId_eccs_sucursal());
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ESPECIALIDADES");
            response.setMensaje("ESPECIALIDAD AGREGADA DE MANERA EXITOSA");
            response.setResponse(especialidadesQuery.save(entity));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ESPECIALIDADES");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> actualizar(ActualizarEspecialidadRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            EspecialidadesEntity entity = especialidadesQuery.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
            entity.setId_eccs_producto(request.getId_eccs_producto());
            entity.setCantidad_producto(request.getCantidad_producto());
            entity.setPrecio_producto(request.getPrecio_producto());
            entity.setCosto_producto(request.getCosto_producto());
            entity.setId_eccs_equivalencia_producto(request.getId_eccs_equivalencia_producto());
            entity.setId_eccs_clasificacion(request.getId_eccs_clasificacion());
            entity.setId_eccs_sucursal(request.getId_eccs_sucursal());
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ESPECIALIDADES");
            response.setMensaje("ESPECIALIDAD ACTUALIZADA DE MANERA EXITOSA");
            response.setResponse(especialidadesQuery.save(entity));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ESPECIALIDADES");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> eliminar(Long id) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            especialidadesQuery.deleteById(id);
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ESPECIALIDADES");
            response.setMensaje("ESPECIALIDAD ELIMINADA DE MANERA EXITOSA");
            response.setResponse(null);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ESPECIALIDADES");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

}
