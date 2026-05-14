package eccs.com.modules.controlventas.catalogocortes.query;

import eccs.com.modules.controlventas.catalogocortes.entity.CatalogoCortesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CatalogoCortesQuery extends JpaRepository<CatalogoCortesEntity, Long> {

    @Query(value = "SELECT * FROM \"controlventas\".fn_get_catalogo_cortes(:idSucursal)", nativeQuery = true)
    List<Map<String, Object>> getCatalogoCortes(@Param("idSucursal") Integer idSucursal);
}
