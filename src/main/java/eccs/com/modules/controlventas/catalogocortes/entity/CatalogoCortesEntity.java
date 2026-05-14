package eccs.com.modules.controlventas.catalogocortes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT 1 AS id")
@Data
public class CatalogoCortesEntity {
    @Id
    private Long id;
}
