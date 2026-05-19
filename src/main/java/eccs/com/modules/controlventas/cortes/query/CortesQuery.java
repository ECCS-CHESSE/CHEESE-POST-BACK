package eccs.com.modules.controlventas.cortes.query;

import eccs.com.modules.controlventas.cortes.entity.CortesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface CortesQuery extends JpaRepository<CortesEntity, Long> {

    @Query(value = "SELECT * FROM \"controlventas\".fn_get_data_corte(:id)", nativeQuery = true)
    List<Map<String, Object>> getCatalogoCortes(
        @Param("id") Integer id
    );

    @Query(value = "SELECT * FROM \"controlventas\".fn_abrir_nuevo_eccs_cortes(:idSucursal, :idEmpleado, :monto)", nativeQuery = true)
    List<Map<String, Object>> getAbrirCorte(
        @Param("idSucursal") Integer idSucursal,
        @Param("idEmpleado") Integer idEmpleado,
        @Param("monto")      BigDecimal monto
    );

    @Query(value = "SELECT * FROM \"controlventas\".fn_cerrar_eccs_corte(:id_eccs_sucursal, :id, :id_eccs_empleado)", nativeQuery = true)
    List<Map<String, Object>> getCerrarCorte(
        @Param("id_eccs_sucursal") Integer id_eccs_sucursal,
        @Param("id_eccs_empleado") Integer id_eccs_empleado,
        @Param("id")      Integer id
    );

}
