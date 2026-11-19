package eccs.com.modules.facturacion.xml.query;

import eccs.com.modules.facturacion.xml.entity.XmlEntity;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface XmlQuery extends JpaRepository<XmlEntity, Long> {

    @Query(value = "SELECT CAST(\"facturacion\".fn_get_xml(:id) AS text)", nativeQuery = true)
    String getXml(@Param("id") Long id);

    @Query(value = """
        SELECT CAST("facturacion".fn_get_facturas_timbradas(
            :correo, :rfc, :claveSucursal, :fechaTimbrado
        ) AS text)
        """, nativeQuery = true)
    String getFacturasTimbradas(
        @Param("correo") String correo,
        @Param("rfc") String rfc,
        @Param("claveSucursal") String claveSucursal,
        @Param("fechaTimbrado") LocalDate fechaTimbrado
    );
}
