package eccs.com.auth.login.service;

import eccs.com.auth.login.dto.AuthRequestDto;

public interface AuthService {
    Object login(AuthRequestDto request);
}
