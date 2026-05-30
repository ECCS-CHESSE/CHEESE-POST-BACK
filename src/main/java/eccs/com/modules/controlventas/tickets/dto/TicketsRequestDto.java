package eccs.com.modules.controlventas.tickets.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketsRequestDto {
    @NotNull
    private Integer id_sucursal;
}
