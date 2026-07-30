package eccs.com.modules.sistemapos.preventa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "eccs_preventa", schema = "sistemapos")
@Data
public class PreventaEntity {
    @Id
    private Long id;
}
