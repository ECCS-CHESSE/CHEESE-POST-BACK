package eccs.com.modules.controlinventarios.productos.query;

import eccs.com.modules.controlinventarios.productos.entity.ProductoServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoServicioQuery extends JpaRepository<ProductoServicioEntity, Long> {

    @Query(value = "SELECT * FROM eccs_producto_servicio WHERE id_eccs_clasificacion = :idClasificacion AND id_eccs_sucursal = :idSucursal AND activo = true", nativeQuery = true)
    List<ProductoServicioEntity> getProductosByClasificacion(@Param("idSucursal") Integer idSucursal, @Param("idClasificacion") Integer idClasificacion);

}
