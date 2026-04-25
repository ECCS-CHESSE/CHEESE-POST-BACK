package eccs.com.auth.login.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import eccs.com.auth.login.dto.AuthRequestDto;
import eccs.com.auth.login.dto.LoginResponseDto;
import eccs.com.auth.login.query.AuthQuery;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.core.services.JwtUtil;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements AuthService {

    private final AuthQuery loginQuery;
    private final JwtUtil jwtUtil;
    private final JsonParserMiddleware jsonParserMiddleware;
    private final ObjectMapper objectMapper;

    @Override
    public Object login(AuthRequestDto request) {
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
