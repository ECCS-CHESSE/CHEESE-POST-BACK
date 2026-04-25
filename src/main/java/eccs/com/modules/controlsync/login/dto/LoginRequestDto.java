package eccs.com.modules.controlsync.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank
    private String usuario;
    @NotBlank
    private String pass;
}
