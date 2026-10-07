package eccs.com.modules.pos.catalogoplataformas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT 1 AS id")
@Data
public class CatalogoPlataformasEntity {
    @Id
    private Long id;
}
