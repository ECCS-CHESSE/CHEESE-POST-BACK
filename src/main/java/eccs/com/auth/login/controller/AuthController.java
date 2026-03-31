package eccs.com.auth.login.controller;

import eccs.com.auth.login.dto.AuthRequestDto;
import eccs.com.auth.login.service.AuthService;
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
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ResponseService responseService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Object>> login(@Valid @RequestBody AuthRequestDto request) {
        Object result = authService.login(request);
        return responseService.ServiceResponse(new ResponseDto<>(), "Login exitoso", "Autenticación correcta", result);
    }
}
