package eccs.com.modules.controlventas.productos.query;

import eccs.com.modules.controlventas.productos.entity.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductosQuery extends JpaRepository<ProductosEntity, Long> {

    @Query(value = "SELECT * FROM \"controlventas\".fn_buscar_eccs_productos_pizzas(:idSucursal, :idEstatus, :descripcion)", nativeQuery = true)
    List<Map<String, Object>> buscarProductos(
        @Param("idSucursal") Integer idSucursal,
        @Param("idEstatus") Integer idEstatus,
        @Param("descripcion") String descripcion
    );
}
