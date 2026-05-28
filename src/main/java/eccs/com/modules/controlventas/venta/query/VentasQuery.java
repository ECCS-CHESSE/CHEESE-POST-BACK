package eccs.com.modules.controlventas.venta.query;

import eccs.com.modules.controlventas.cortes.entity.CortesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VentasQuery extends JpaRepository<CortesEntity, Long> {


    @Query(value = "SELECT * FROM \"controlventas\".fn_create_comprobante_venta(:id_eccs_sucursal, :id_eccs_cliente, :id_eccs_empleado, :id_eccs_tipo_pedido)", nativeQuery = true)
    List<Map<String, Object>> getCrearVenta(
        @Param("id_eccs_sucursal") Integer id_eccs_sucursal,
        @Param("id_eccs_cliente") Integer id_eccs_cliente,
        @Param("id_eccs_empleado") Integer id_eccs_empleado,
        @Param("id_eccs_tipo_pedido") Integer id_eccs_tipo_pedido
    );
  
    @Query(value = "SELECT * FROM \"controlventas\".fn_get_data_comprobante_ventas(:id_eccs_sucursal, :id)", nativeQuery = true)
    List<Map<String, Object>> getDataVenta(
        @Param("id_eccs_sucursal") Integer id_eccs_sucursal,
        @Param("id") Integer id
    );

}
