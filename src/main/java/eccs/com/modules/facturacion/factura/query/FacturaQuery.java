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
}
