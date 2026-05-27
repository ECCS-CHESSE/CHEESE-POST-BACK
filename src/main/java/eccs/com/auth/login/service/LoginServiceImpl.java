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
        claims.put("id_eccs_cliente", result.getUsuario().getId_eccs_cliente());
        claims.put("id_estatus_empleado", result.getUsuario().getId_estatus_empleado());
        claims.put("eccs_estatus", result.getUsuario().getEccs_estatus());
        claims.put("id_eccs_sucursal", result.getUsuario().getId_eccs_sucursal());
        claims.put("id_eccs_sucursal_domicilio", result.getUsuario().getId_eccs_sucursal_domicilio());
        claims.put("empleado", result.getUsuario().getEmpleado());
        claims.put("eccs_url", result.getUsuario().getEccs_url());

        String token = jwtUtil.generateToken(request.getUsuario(), claims);
        return Map.of("token", token, "empleado", result);
    }
}
