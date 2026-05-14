package eccs.com.modules.controlventas.catalogocortes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CatalogoCortesRequestDto {
    @NotNull
    private Integer id_sucursal;
}
