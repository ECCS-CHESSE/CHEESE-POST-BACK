package eccs.com.modules.facturacion.xml.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class XmlRequestDto {

    @NotNull(message = "id es requerido")
    private Long id;
}
