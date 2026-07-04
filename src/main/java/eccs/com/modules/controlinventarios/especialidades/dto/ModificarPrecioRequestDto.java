package eccs.com.modules.controlinventarios.especialidades.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ModificarPrecioRequestDto {
    @NotNull
    private Long id;
    @NotNull
    private BigDecimal cantidad_producto;
    @NotNull
    private BigDecimal precio_producto;
}
