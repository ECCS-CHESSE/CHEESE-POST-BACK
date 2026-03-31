package eccs.com.auth.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDto {
    @NotBlank
    private String usuario;
    @NotBlank
    private String pass;
}
