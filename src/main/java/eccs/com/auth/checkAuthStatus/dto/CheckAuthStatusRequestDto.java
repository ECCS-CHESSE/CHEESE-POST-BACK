package eccs.com.auth.checkAuthStatus.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CheckAuthStatusRequestDto {
    @NotBlank
    private String token;
}
