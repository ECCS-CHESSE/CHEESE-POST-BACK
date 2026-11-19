package eccs.com.modules.facturacion.xml.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
public class FacturasTimbradasRequestDto {

    @NotBlank
    private String correo;

    @NotBlank
    private String rfc;

    @NotBlank
    private String clave_sucursal;

    @NotNull
    private LocalDate fecha_timbrado;
}
