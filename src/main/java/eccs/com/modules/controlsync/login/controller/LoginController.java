package eccs.com.modules.controlsync.login.controller;

import eccs.com.modules.controlsync.login.dto.LoginRequestDto;
import eccs.com.modules.controlsync.login.service.LoginService;
import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.services.ResponseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlsync")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final ResponseService responseService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Object>> login(@Valid @RequestBody LoginRequestDto request) {
        Object result = loginService.login(request);
        return responseService.ServiceResponse(new ResponseDto<>(), "Login exitoso", "Autenticación correcta", result);
    }
}
