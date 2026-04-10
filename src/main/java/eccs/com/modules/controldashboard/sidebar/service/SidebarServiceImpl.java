package eccs.com.modules.controldashboard.sidebar.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controldashboard.sidebar.query.SidebarQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SidebarServiceImpl implements SidebarService {

    private final SidebarQuery sidebarQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getMenu(Integer id) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseField(sidebarQuery.findMenuByIdEmpleado(id), "app_menu");
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL DASHBOARD - SIDEBAR - MENU");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL DASHBOARD - SIDEBAR - MENU");
            response.setMensaje("Error al obtener el menú: " + e.getMessage());
            response.setResponse("Error en el proceso de consulta");
        }
        return response;
    }
}
