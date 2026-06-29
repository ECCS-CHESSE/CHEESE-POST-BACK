package eccs.com.modules.controlinventarios.productos.query;

import eccs.com.modules.controlinventarios.productos.entity.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ProductosQuery extends JpaRepository<ProductosEntity, Long> {

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_catalogo_eccs_productos(:idSucursal, :idClasificacion)", nativeQuery = true)
    List<Map<String, Object>> getCatalogoProductos(
        @Param("idSucursal") Integer idSucursal,
        @Param("idClasificacion") Integer idClasificacion
    );

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_catalogo_eccs_categorias(:idSucursal)", nativeQuery = true)
    List<Map<String, Object>> getCatalogoCategorias(
        @Param("idSucursal") Integer idSucursal
    );

}
