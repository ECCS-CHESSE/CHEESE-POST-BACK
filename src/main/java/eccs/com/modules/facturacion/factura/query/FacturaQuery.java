package eccs.com.modules.facturacion.factura.query;

import eccs.com.modules.facturacion.factura.entity.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FacturaQuery extends JpaRepository<FacturaEntity, Long> {

    @Query(value = "SELECT * FROM \"facturacion\".fn_get_data_factura(:folio, :sucursal)", nativeQuery = true)
    List<Map<String, Object>> getDataFactura(
        @Param("folio") Integer folio,
        @Param("sucursal") String sucursal
    );

    @Query(value = "SELECT * FROM \"facturacion\".fn_get_comprobante_factura(:id)", nativeQuery = true)
    List<Map<String, Object>> getDataComprobante(
        @Param("id") Integer id
    );

    @Query(value = "SELECT * FROM \"facturacion\".fn_get_data_comprobante_cliente(:id)", nativeQuery = true)
    List<Map<String, Object>> getDataComprobanteCliente(
        @Param("id") Integer id
    );

    @Query(value = "SELECT * FROM \"facturacion\".fn_create_eccs_cliente(:id, :idempleado, :nombre, :rfc, :telefono, :codigopostal, :correo, :id_regimenfical, :id_uso_cfdi)", nativeQuery = true)
    List<Map<String, Object>> crearCliente(
        @Param("id") Integer id,
        @Param("idempleado") Integer idempleado,
        @Param("nombre") String nombre,
        @Param("rfc") String rfc,
        @Param("telefono") Long telefono,
        @Param("codigopostal") String codigopostal,
        @Param("correo") String correo,
        @Param("id_regimenfical") Integer id_regimenfical,
        @Param("id_uso_cfdi") Integer id_uso_cfdi
    );
}
