package eccs.com.modules.controlsync.login.service;

import eccs.com.modules.controlsync.login.dto.LoginRequestDto;
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

    @Override
    public Object login(LoginRequestDto request) {
        List<Map<String, Object>> rows = loginQuery.findByLogin(request.getUsuario(), request.getPass());

        if (rows == null || rows.isEmpty())
            throw new RuntimeException("Credenciales inválidas");

        Object empleado = jsonParserMiddleware.parseFunction(rows);

        Map<String, Object> claims = new HashMap<>();
        rows.get(0).forEach((k, v) -> claims.put(k, v != null ? v.toString() : null));

        String token = jwtUtil.generateToken(request.getUsuario(), claims);
        return Map.of("token", token, "empleado", empleado);
    }
}
