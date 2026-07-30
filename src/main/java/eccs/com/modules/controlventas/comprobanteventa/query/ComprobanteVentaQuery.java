package eccs.com.modules.controlventas.comprobanteventa.query;

import eccs.com.modules.controlventas.comprobanteventa.entity.ComprobanteVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ComprobanteVentaQuery extends JpaRepository<ComprobanteVentaEntity, Long> {

    @Query(value = "SELECT * FROM \"controlventas\".fn_create_comprobante_venta(:idSucursal, :idEmpleado, :idCliente, :idTipoComprobante)", nativeQuery = true)
    List<Map<String, Object>> createComprobanteVenta(
        @Param("idSucursal")         Integer idSucursal,
        @Param("idCliente")          Integer idCliente,
        @Param("idEmpleado")         Integer idEmpleado,
        @Param("idTipoComprobante") Integer idTipoComprobante
    );
  
    @Query(value = "SELECT * FROM \"controlventas\".fn_get_data_comprobante_ventas_info(:idSucursal, :idComprobante )", nativeQuery = true)
    List<Map<String, Object>> getDataDetalle(
        @Param("idSucursal")         Integer idSucursal,
        @Param("idComprobante")         Integer idComprobante
    );
 
    @Query(value = "SELECT * FROM \"controlventas\".fn_eliminar_producto_comprobante_ventas_info(:idSucursal, :id )", nativeQuery = true)
    List<Map<String, Object>> getEliminarProducto(
        @Param("idSucursal")         Integer idSucursal,
        @Param("id")         Integer id
    );

    @Query(value = "SELECT * FROM \"controlventas\".fn_get_data_comprobante_ventas(:idSucursal, :idComprobante )", nativeQuery = true)
    List<Map<String, Object>> getData(
        @Param("idSucursal")         Integer idSucursal,
        @Param("idComprobante")         Integer idComprobante
    );

    @Query(value = "SELECT * FROM \"controlventas\".fn_agregar_eccs_productos_pizzas_venta(:idSucursal, :idVenta, :idEmpleado, :precio, :tamano, :productoDescripcion)", nativeQuery = true)
    List<Map<String, Object>> agregarProducto(
        @Param("idSucursal")           Integer idSucursal,
        @Param("idVenta")              Integer idVenta,
        @Param("idEmpleado")           Integer idEmpleado,
        @Param("precio")               Double precio,
        @Param("tamano")               String tamano,
        @Param("productoDescripcion")  String productoDescripcion
    );

    @Query(value = "SELECT * FROM \"controlventas\".fn_cancelar_comprobante_ventas(:idSucursal, :id)", nativeQuery = true)
    List<Map<String, Object>> cancelarComprobanteVenta(
        @Param("idSucursal") Integer idSucursal,
        @Param("id")         Integer id
    );

    @Query(value = "SELECT * FROM \"controlventas\".fn_update_eccs_cliente_comprobante_venta(:id, :idCliente)", nativeQuery = true)
    List<Map<String, Object>> updateClienteComprobanteVenta(
        @Param("id")        Integer id,
        @Param("idCliente") Integer idCliente
    );

    @Query(value = "SELECT * FROM \"controlventas\".fn_item_aumenta(:idVenta, :idCantidad)", nativeQuery = true)
    List<Map<String, Object>> itemAumenta(
        @Param("idVenta")    Integer idVenta,
        @Param("idCantidad") Integer idCantidad
    );

    @Query(value = "SELECT * FROM \"controlventas\".fn_eccs_compobante_cancelar(:idSucursal, :idVenta)", nativeQuery = true)
    List<Map<String, Object>> eccsCancelarComprobante(
        @Param("idSucursal") Integer idSucursal,
        @Param("idVenta")    Integer idVenta
    );

}
