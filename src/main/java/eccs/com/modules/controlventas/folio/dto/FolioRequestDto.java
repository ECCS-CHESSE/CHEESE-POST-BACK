package eccs.com.modules.controlventas.folio.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FolioRequestDto {

    @NotNull
    private Integer folio;
}
