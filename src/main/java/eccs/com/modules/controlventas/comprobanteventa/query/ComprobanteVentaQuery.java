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

    @Query(value = "SELECT * FROM \"controlventas\".fn_create_comprobante_venta(:idSucursal, :idEmpleado, :idCliente, :idClienteDomicilio)", nativeQuery = true)
    List<Map<String, Object>> createComprobanteVenta(
        @Param("idSucursal")         Integer idSucursal,
        @Param("idEmpleado")         Integer idEmpleado,
        @Param("idCliente")          Integer idCliente,
        @Param("idClienteDomicilio") Integer idClienteDomicilio
    );

}
