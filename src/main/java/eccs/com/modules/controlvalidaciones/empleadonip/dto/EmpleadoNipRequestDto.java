package eccs.com.modules.controlvalidaciones.empleadonip.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmpleadoNipRequestDto {
    @NotNull
    private Integer idEmpleado;
    @NotBlank
    private String nip;
}
