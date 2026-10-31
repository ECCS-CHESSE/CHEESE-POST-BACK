package eccs.com.modules.pos.preventa.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import eccs.com.modules.pos.preventa.entity.PreventaEntity;

import java.util.List;
import java.util.Map;

@Repository
public interface PreventaQuery extends JpaRepository<PreventaEntity, Long> {

    @Query(value = "SELECT * FROM \"sistemapos\".fn_get_precios_pizzas(:idSucursal)", nativeQuery = true)
    List<Map<String, Object>> getPreciosPizzas(@Param("idSucursal") Integer idSucursal);

    @Query(value = "SELECT * FROM \"pos\".fn_get_data_ingredientes_produccion_info(:idSucursal, :id_venta, :id_especialidad )", nativeQuery = true)
    List<Map<String, Object>> getDataIngredientesProduccionInfo(
        @Param("idSucursal") Integer idSucursal,
        @Param("id_venta") Integer id_venta,
        @Param("id_especialidad") Integer id_especialidad
    );

    @Query(value = "SELECT * FROM \"pos\".fn_get_eliminar_ingrediente_preventa(:idSucursal, :idVenta, :idEspecialidad)", nativeQuery = true)
    List<Map<String, Object>> eliminarIngredientePreventa(
        @Param("idSucursal") Integer idSucursal,
        @Param("idVenta") Integer idVenta,
        @Param("idEspecialidad") Integer idEspecialidad
    );
}
