package eccs.com.modules.controlsync.login.service;

import eccs.com.modules.controlsync.login.dto.LoginRequestDto;

public interface LoginService {
    Object login(LoginRequestDto request);
}
