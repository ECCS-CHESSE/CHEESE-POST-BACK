package eccs.com.modules.controlventas.clientes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientesBuscarRequestDto {
   
@NotNull(message = "descripcion es requerido") private String descripcion;

}
