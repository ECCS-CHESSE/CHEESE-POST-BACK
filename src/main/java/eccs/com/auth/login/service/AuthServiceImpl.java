package eccs.com.auth.login.service;

import eccs.com.auth.login.dto.AuthRequestDto;
import eccs.com.auth.login.query.AuthQuery;
import eccs.com.core.services.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthQuery authQuery;
    private final JwtUtil jwtUtil;

    @Override
    public Object login(AuthRequestDto request) {
        Map<String, Object> empleado = authQuery.findByUsuarioAndPass(request.getUsuario(), request.getPass());

        if (empleado == null || empleado.isEmpty())
            throw new RuntimeException("Credenciales inválidas");

        String token = jwtUtil.generateToken(request.getUsuario(), empleado);
        return Map.of("token", token, "empleado", empleado);
    }
}
