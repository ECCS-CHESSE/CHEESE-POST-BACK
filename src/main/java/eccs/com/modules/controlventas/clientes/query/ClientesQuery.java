package eccs.com.modules.controlventas.clientes.query;

import eccs.com.modules.controlventas.catalogoclientes.entity.CatalogoClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ClientesQuery extends JpaRepository<CatalogoClientesEntity, Long> {

    @Query(value = "SELECT * FROM \"controlventas\".fn_create_eccs_cliente(:idVenta, :idSucursal, :idEmpleado, :nombre, :apellidoPaterno, :apellidoMaterno, :telefono)", nativeQuery = true)
    List<Map<String, Object>> getCrearCliente(
        @Param("idVenta") Integer idVenta,
        @Param("idSucursal") Integer idSucursal,
        @Param("idEmpleado") Integer idEmpleado,
        @Param("nombre") String nombre,
        @Param("apellidoPaterno") String apellidoPaterno,
        @Param("apellidoMaterno") String apellidoMaterno,
        @Param("telefono") Long telefono
    );

    @Query(value = "SELECT * FROM \"controlventas\".fn_get_catalogo_eccs_clientes(:descripcion)", nativeQuery = true)
    List<Map<String, Object>> getBusquedaCliente(
        @Param("descripcion") String descripcion
    );

    @Query(value = "SELECT * FROM \"controlventas\".fn_get_data_eccs_cliente_domicilio(:idCliente)", nativeQuery = true)
    List<Map<String, Object>> getDomicilioCliente(
        @Param("idCliente") Integer idCliente
    );

}
