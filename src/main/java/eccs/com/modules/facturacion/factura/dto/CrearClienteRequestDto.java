package eccs.com.modules.facturacion.factura.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CrearClienteRequestDto {

    @NotNull(message = "id es requerido")
    private Integer id;

    @NotNull(message = "idempleado es requerido")
    private Integer idempleado;

    @NotBlank(message = "nombre es requerido")
    private String nombre;

    @NotNull(message = "telefono es requerido")
    private Long telefono;

    @NotBlank(message = "codigopostal es requerido")
    private String codigopostal;

    @NotBlank(message = "correo es requerido")
    private String correo;
}
