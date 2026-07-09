package eccs.com.modules.controlinventarios.ordenproduccion.query;

import eccs.com.modules.controlinventarios.ordenproduccion.entity.OrdenProduccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface OrdenProduccionQuery extends JpaRepository<OrdenProduccionEntity, Long> {

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_insertar_ingredientes_orden_produccion(:idSucursal, :id, :id_venta)", nativeQuery = true)
    List<Map<String, Object>> insertarIngredientesOrden(@Param("idSucursal") Integer idSucursal, @Param("id") Integer id, @Param("id_venta") Integer id_venta);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_eliminar_ingredientes_orden_produccion(:idSucursal, :idEspecialidad1, :idEspecialidad2, :idVenta)", nativeQuery = true)
    List<Map<String, Object>> limpiarIngredientesOrden(@Param("idSucursal") Integer idSucursal, @Param("idEspecialidad1") Integer idEspecialidad1, @Param("idEspecialidad2") Integer idEspecialidad2, @Param("idVenta") Integer idVenta);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_insertar_salsa(:idSucursal, :idProducto, :idVenta)", nativeQuery = true)
    List<Map<String, Object>> insertarSalsa(@Param("idSucursal") Integer idSucursal, @Param("idProducto") Integer idProducto, @Param("idVenta") Integer idVenta);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_insertar_orilla_queso(:idSucursal, :idProducto, :idVenta)", nativeQuery = true)
    List<Map<String, Object>> insertarOrillaQueso(@Param("idSucursal") Integer idSucursal, @Param("idProducto") Integer idProducto, @Param("idVenta") Integer idVenta);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_update_categorias_arma_tu_pizza(:idSucursal, :idVenta, :idEquivalencia)", nativeQuery = true)
    List<Map<String, Object>> updateCategoriasArmaTuPizza(@Param("idSucursal") Integer idSucursal, @Param("idVenta") Integer idVenta, @Param("idEquivalencia") Integer idEquivalencia);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_agregar_especificaciones_orden_pizza(:idSucursal, :idVenta, :especificaciones)", nativeQuery = true)
    List<Map<String, Object>> agregarEspecificaciones(@Param("idSucursal") Integer idSucursal, @Param("idVenta") Integer idVenta, @Param("especificaciones") String especificaciones);

}
