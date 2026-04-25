
package eccs.com.modules.controldashboard.sidebar.controller;


import eccs.com.modules.controldashboard.sidebar.service.SidebarService;
import eccs.com.core.dtos.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controldashboard")
@RequiredArgsConstructor
public class SidebarController {

    private final SidebarService sidebarService;

    @PostMapping("/getMenu/{id}")
    public ResponseEntity<ResponseDto<Object>> getMenu(@PathVariable Integer id) {
        return ResponseEntity.ok(sidebarService.getMenu(id));
    }
}
