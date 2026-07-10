package eccs.com.modules.controlventas.cancelaciones.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FolioRequestDto {

    @NotNull
    private Integer folio;
}
