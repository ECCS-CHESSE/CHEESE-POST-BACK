package eccs.com.modules.controlventas.clientes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientesActualizarDomicilioRequestDto {
    @NotNull(message = "id_ es requerido")
    private Integer id_;
    @NotNull(message = "tel es requerido")
    private String tel;
    @NotNull(message = "calle es requerido")
    private String calle;
    @NotNull(message = "cp es requerido")
    private String cp;
    @NotNull(message = "fraccionamiento es requerido")
    private String fraccionamiento;
    @NotNull(message = "referencia es requerido")
    private String referencia;
    @NotNull(message = "entrecalles es requerido")
    private String entrecalles;
    @NotNull(message = "id_eccs_empleado es requerido")
    private Integer id_eccs_empleado;
    @NotNull(message = "id_eccs_sucursal es requerido")
    private Integer id_eccs_sucursal;
}
