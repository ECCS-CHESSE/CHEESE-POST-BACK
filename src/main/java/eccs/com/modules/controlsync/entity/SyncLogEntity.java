package eccs.com.modules.controlsync.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "eccs_sync_log")
@Data
public class SyncLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "query_fallido", columnDefinition = "TEXT")
    private String queryFallido;

    @Column(name = "error_msg", columnDefinition = "TEXT")
    private String errorMsg;

    @Column(name = "fecha")
    private LocalDateTime fecha = LocalDateTime.now();

    @Column(name = "resuelto")
    private Boolean resuelto = false;

    @Column(name = "id_sucursal")
    private Integer idSucursal;
}
