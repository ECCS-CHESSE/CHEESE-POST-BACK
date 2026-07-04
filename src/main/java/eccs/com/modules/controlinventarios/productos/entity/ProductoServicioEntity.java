package eccs.com.modules.controlinventarios.productos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "eccs_producto_servicio")
@Data
public class ProductoServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String codigo;

    @Column(name = "id_eccs_tipo")
    private Integer idEccsTipo = 1;

    @Column(name = "id_eccs_clasificacion")
    private Integer idEccsClasificacion = 8;

    @Column(name = "id_sat_claveprodserv")
    private Integer idSatClaveprodserv = 51885;

    @Column(name = "id_sat_unidad_aduana")
    private Integer idSatUnidadAduana = 6;

    @Column(name = "id_eccs_estatus")
    private Integer idEccsEstatus = 1;

    @Column(name = "id_sat_claveunidad")
    private Long idSatClaveunidad = 678L;

    @Column(name = "id_sat_objetoimp")
    private Integer idSatObjetoimp = 2;

    private Boolean activo = true;

    @Column(name = "id_eccs_sucursal")
    private Integer idEccsSucursal;

}
