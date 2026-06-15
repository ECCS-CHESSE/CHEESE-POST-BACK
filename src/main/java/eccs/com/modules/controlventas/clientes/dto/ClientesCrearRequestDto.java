package eccs.com.modules.controlventas.clientes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientesCrearRequestDto {
   
@NotNull(message = "id es requerido venta") private Integer id;
@NotNull(message = "id_sucursal es requerido") private Integer id_sucursal;
@NotNull(message = "id_empleado es requerido") private Integer id_empleado;
@NotNull(message = "nombre es requerido") private String nombre;
@NotNull(message = "apellidoPaterno es requerido") private String apellidoPaterno;
@NotNull(message = "apellidoMaterno es requerido") private String apellidoMaterno;
@NotNull(message = "telefono es requerido") private Long telefono;


}
