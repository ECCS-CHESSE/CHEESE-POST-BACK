package eccs.com.auth.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDto {
    @NotBlank
    private String user;
    @NotBlank
    private String pass;
}
