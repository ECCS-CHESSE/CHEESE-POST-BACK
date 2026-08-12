package eccs.com.modules.controlventas.pagos.query;

import eccs.com.modules.controlventas.pagos.entity.PagosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PagosQuery extends JpaRepository<PagosEntity, Long> {

    @Query(value = "SELECT * FROM \"controlventas\".fn_get_data_pago(:id_venta)", nativeQuery = true)
    List<Map<String, Object>> getDataPago(@Param("id_venta") Integer id_venta);

    @Query(value = "SELECT * FROM \"controlventas\".fn_ins_pago(:id_sucursal, :id, :id_empleado, :id_sat_forma_pago, :importe)", nativeQuery = true)
    List<Map<String, Object>> insertPago(
        @Param("id_sucursal") Integer id_sucursal,
        @Param("id") Integer id,
        @Param("id_empleado") Integer id_empleado,
        @Param("id_sat_forma_pago") Integer id_sat_forma_pago,
        @Param("importe") Double importe
    );

}
