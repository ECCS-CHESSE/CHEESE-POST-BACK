package eccs.com.auth.checkAuthStatus.controller;

import eccs.com.auth.checkAuthStatus.dto.CheckAuthStatusRequestDto;
import eccs.com.auth.checkAuthStatus.service.CheckAuthStatusService;
import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.services.ResponseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class CheckAuthStatusController {

    private final CheckAuthStatusService checkAuthStatusService;
    private final ResponseService responseService;

    @PostMapping("/checkAuthStatus")
    public ResponseEntity<ResponseDto<Object>> checkAuthStatus(@Valid @RequestBody CheckAuthStatusRequestDto request) {
        Object result = checkAuthStatusService.checkAuthStatus(request.getToken());
        return responseService.ServiceResponse(new ResponseDto<>(), "Check Auth", "Token validado", result);
    }
}
