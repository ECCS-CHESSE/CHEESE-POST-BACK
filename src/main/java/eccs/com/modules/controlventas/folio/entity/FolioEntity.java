package eccs.com.modules.controlventas.folio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "folio_entity")
@Data
public class FolioEntity {

    @Id
    private Long id;
}
