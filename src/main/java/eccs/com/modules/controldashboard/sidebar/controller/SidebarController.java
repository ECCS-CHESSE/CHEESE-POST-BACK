package eccs.com.modules.controldashboard.sidebar.controller;

import eccs.com.modules.controldashboard.sidebar.service.SidebarService;
import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.services.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controldashboard")
@RequiredArgsConstructor
public class SidebarController {

    private final SidebarService sidebarService;
    private final ResponseService responseService;

    @PostMapping("/getMenu/{id}")
    public ResponseEntity<ResponseDto<Object>> getMenu(@PathVariable Integer id) {
        Object result = sidebarService.getMenu(id);
        return responseService.ServiceResponse(new ResponseDto<>(), "ECCS - CONTROL DASHBOARD - SIDEBAR - MENU", "CONSULTA DE MANERA EXITOSA", result);
    }
}
