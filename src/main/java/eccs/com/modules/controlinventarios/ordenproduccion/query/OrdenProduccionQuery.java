package eccs.com.modules.controlinventarios.ordenproduccion.query;

import eccs.com.modules.controlinventarios.ordenproduccion.entity.OrdenProduccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface OrdenProduccionQuery extends JpaRepository<OrdenProduccionEntity, Long> {

    @Query(value = "SELECT * FROM \"pos\".fn_insertar_ingredientes_orden_produccion(:idSucursal, :id, :id_venta, :id_lado_pizza, :id_pc, :id_tamano )", nativeQuery = true)
    List<Map<String, Object>> insertarIngredientesOrden(@Param("idSucursal") Integer idSucursal, @Param("id") Integer id, @Param("id_venta") Integer id_venta, @Param("id_lado_pizza") Integer id_lado_pizza, @Param("id_pc") Integer id_pc, @Param("id_tamano") Integer id_tamano  );


    @Query(value = "SELECT * FROM \"controlinventarios\".fn_eliminar_ingredientes_orden_produccion(:idSucursal, :idEspecialidad1, :idEspecialidad2, :idVenta)", nativeQuery = true)
    List<Map<String, Object>> limpiarIngredientesOrden(@Param("idSucursal") Integer idSucursal, @Param("idEspecialidad1") Integer idEspecialidad1, @Param("idEspecialidad2") Integer idEspecialidad2, @Param("idVenta") Integer idVenta);

    @Query(value = "SELECT * FROM \"pos\".fn_insertar_salsa(:idSucursal, :idProducto, :idVenta, :idPc, :idTamano)", nativeQuery = true)
    List<Map<String, Object>> insertarSalsa(
        @Param("idSucursal") Integer idSucursal, 
        @Param("idProducto") Integer idProducto,
        @Param("idVenta") Integer idVenta,
        @Param("idPc") Integer idPc,
        @Param("idTamano") Integer idTamano
    );

    @Query(value = "SELECT * FROM \"pos\".fn_insertar_orilla_queso(:idSucursal, :idProducto, :idVenta, :idPc, :idTamano)", nativeQuery = true)
    List<Map<String, Object>> insertarOrillaQueso(
        @Param("idSucursal") Integer idSucursal, 
        @Param("idProducto") Integer idProducto,
        @Param("idVenta") Integer idVenta,
        @Param("idPc") Integer idPc,
        @Param("idTamano") Integer idTamano
    
    );

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_update_categorias_arma_tu_pizza(:idSucursal, :idVenta, :idEquivalencia)", nativeQuery = true)
    List<Map<String, Object>> updateCategoriasArmaTuPizza(@Param("idSucursal") Integer idSucursal, @Param("idVenta") Integer idVenta, @Param("idEquivalencia") Integer idEquivalencia);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_agregar_especificaciones_orden_pizza(:idSucursal, :idVenta, :especificaciones)", nativeQuery = true)
    List<Map<String, Object>> agregarEspecificaciones(@Param("idSucursal") Integer idSucursal, @Param("idVenta") Integer idVenta, @Param("especificaciones") String especificaciones);

    @Query(value = "SELECT * FROM \"pos\".fn_modal_catalogo_ingredientes_orden_produccion_derecho(:idEquivalencia, :idSucursal, :idVenta )", nativeQuery = true)
    List<Map<String, Object>> getIngredientesOrdenDerecho(  @Param("idEquivalencia") Integer idEquivalencia, @Param("idSucursal") Integer idSucursal );

    @Query(value = "SELECT * FROM \"pos\".fn_modal_catalogo_ingredientes_orden_produccion_izquierdo(:id_venta, :id_pc, :idEquivalencia, :idSucursal)", nativeQuery = true)
    List<Map<String, Object>> getIngredientesOrdenIzquierdo( @Param("id_venta") Integer id_venta, @Param("id_pc") Integer id_pc,  @Param("idEquivalencia") Integer idEquivalencia, @Param("idSucursal") Integer idSucursal);

    @Query(value = "SELECT * FROM \"pos\".fn_eliminar_ingrediente_orden_produccion(:idSucursal, :id)", nativeQuery = true)
    List<Map<String, Object>> eliminarIngredienteOrden(@Param("idSucursal") Integer idSucursal, @Param("id") Integer id);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_data_ingredientes_data_derecha(:idSucursal, :idEspecialidad)", nativeQuery = true)
    List<Map<String, Object>> getDataIngredientesDataDerecha(@Param("idSucursal") Integer idSucursal, @Param("idEspecialidad") Integer idEspecialidad);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_data_ingredientes_data_izquierda(:idSucursal, :idEspecialidad)", nativeQuery = true)
    List<Map<String, Object>> getDataIngredientesDataIzquierda(@Param("idSucursal") Integer idSucursal, @Param("idEspecialidad") Integer idEspecialidad);


    @Query(value = "SELECT * FROM \"pos\".fn_get_eliminar_ingredientes_izquierda_derecha(:idSucursal, :idVenta, :idEspecialidad, :idLado, :idPc)", nativeQuery = true)
    List<Map<String, Object>> eliminarIngredientesIzquierda(@Param("idSucursal") Integer idSucursal, @Param("idVenta") Integer idVenta, @Param("idEspecialidad") Integer idEspecialidad, @Param("idLado") Integer idLado, @Param("idPc") Integer idPc);

    @Query(value = "SELECT * FROM \"pos\".fn_get_eliminar_ingredientes_izquierda_derecha(:idSucursal, :idVenta, :idEspecialidad, :idLado, :idPc)", nativeQuery = true)
    List<Map<String, Object>> eliminarIngredientesDerecha(@Param("idSucursal") Integer idSucursal, @Param("idVenta") Integer idVenta, @Param("idEspecialidad") Integer idEspecialidad, @Param("idLado") Integer idLado, @Param("idPc") Integer idPc);

    @Query(value = "SELECT * FROM \"pos\".fn_get_total_orden_produccion(:idSucursal, :id_venta, :id_pc, :id_tamano )", nativeQuery = true)
    List<Map<String, Object>> getTotalOrdenProduccion(
        @Param("idSucursal")    Integer idSucursal, 
        @Param("id_venta")      Integer id_venta, 
        @Param("id_pc")         Integer id_pc, 
        @Param("id_tamano")     Integer id_tamano
    );

    @Query(value = "SELECT * FROM \"pos\".fn_insertar_ingrediente(:idSucursal, :idVenta, :id_ingrediente, :idEspecialidad, :idTamano, :idPC,  :idLadoPizza )", nativeQuery = true)
    List<Map<String, Object>> insertarIngredienteIzquierdo( 
        @Param("idSucursal") Integer idSucursal, 
        @Param("idVenta") Integer idVenta,
        @Param("id_ingrediente") Integer id_ingrediente, 
        @Param("idEspecialidad") Integer idEspecialidad, 
        @Param("idTamano") Integer idTamano,
        @Param("idPC") Integer idPC,
        @Param("idLadoPizza") Integer idLadoPizza
    
    );

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_insertar_ingrediente_derecho(:idSucursal, :idVenta, :idIngrediente)", nativeQuery = true)
    List<Map<String, Object>> insertarIngredienteDerecho(@Param("idSucursal") Integer idSucursal, @Param("idVenta") Integer idVenta, @Param("idIngrediente") Integer idIngrediente);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_limpiar_armado_pizza(:idSucursal, :idVenta, :idConfig)", nativeQuery = true)
    List<Map<String, Object>> limpiarArmadoPizza(@Param("idSucursal") Integer idSucursal, @Param("idVenta") Long idVenta, @Param("idConfig") String idConfig);

}
