package eccs.com.auth.login.service;

import eccs.com.auth.login.dto.AuthRequestDto;
import eccs.com.auth.login.query.AuthQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthQuery authQuery;

    @Override
    public Object login(AuthRequestDto request) {
        return authQuery.findByUserAndPass(request.getUser(), request.getPass());
    }
}
