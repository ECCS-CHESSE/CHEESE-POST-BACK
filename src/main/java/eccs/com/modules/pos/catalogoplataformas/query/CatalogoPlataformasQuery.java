package eccs.com.modules.pos.catalogoplataformas.query;

import eccs.com.modules.pos.catalogoplataformas.entity.CatalogoPlataformasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CatalogoPlataformasQuery extends JpaRepository<CatalogoPlataformasEntity, Long> {

    @Query(value = "SELECT * FROM \"pos\".fn_get_data_catalogo_plataformas(:idSucursal)", nativeQuery = true)
    List<Map<String, Object>> getCatalogoPlataformas(@Param("idSucursal") Integer idSucursal);
}
