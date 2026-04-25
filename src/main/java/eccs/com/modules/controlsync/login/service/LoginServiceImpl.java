package eccs.com.modules.controlsync.login.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import eccs.com.modules.controlsync.login.dto.LoginRequestDto;
import eccs.com.modules.controlsync.login.dto.LoginResponseDto;
import eccs.com.modules.controlsync.login.query.LoginQuery;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.core.services.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginQuery loginQuery;
    private final JwtUtil jwtUtil;
    private final JsonParserMiddleware jsonParserMiddleware;
    private final ObjectMapper objectMapper;

    @Override
    public Object login(LoginRequestDto request) {
        List<Map<String, Object>> rows = loginQuery.findByLogin(request.getUsuario(), request.getPass());

        if (rows == null || rows.isEmpty())
            throw new RuntimeException("Credenciales inválidas");

        LoginResponseDto result = objectMapper.convertValue(jsonParserMiddleware.parseFunction(rows), LoginResponseDto.class);

        if (!result.isSuccess() || result.getUsuario() == null)
            return Map.of("success", false, "mensaje", result.getMensaje());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id_eccs_empleado", result.getUsuario().getId_eccs_empleado());
        claims.put("empleado", result.getUsuario().getEmpleado());
        claims.put("estatus", result.getUsuario().getEstatus());

        String token = jwtUtil.generateToken(request.getUsuario(), claims);
        return Map.of("token", token, "empleado", result);
    }
}
