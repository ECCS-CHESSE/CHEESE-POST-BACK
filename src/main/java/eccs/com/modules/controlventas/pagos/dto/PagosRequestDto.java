package eccs.com.modules.controlventas.pagos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagosRequestDto {

    @NotNull
    private Integer id_venta;

}
