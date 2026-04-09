package eccs.com.auth.checkAuthStatus.service;

import eccs.com.core.services.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckAuthStatusServiceImpl implements CheckAuthStatusService {

    private final JwtUtil jwtUtil;

    @Override
    public Object checkAuthStatus(String token) {
        return jwtUtil.validateToken(token);
    }
}
