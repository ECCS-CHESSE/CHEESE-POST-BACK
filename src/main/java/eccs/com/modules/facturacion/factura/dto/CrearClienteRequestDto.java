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

    @NotBlank(message = "rfc es requerido")
    private String rfc;

    @NotNull(message = "telefono es requerido")
    private Long telefono;

    @NotBlank(message = "codigopostal es requerido")
    private String codigopostal;

    @NotBlank(message = "correo es requerido")
    private String correo;

    @NotNull(message = "id_regimenfical es requerido")
    private Integer id_regimenfical;

    @NotNull(message = "id_uso_cfdi es requerido")
    private Integer id_uso_cfdi;
}
