package eccs.com.modules.controlempresa.catalogosucursales.query;

import eccs.com.modules.controlempresa.catalogosucursales.entity.CatalogoSucursalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CatalogoSucursalesQuery extends JpaRepository<CatalogoSucursalesEntity, Long> {

    @Query(value = "SELECT * FROM \"controlempresa\".fn_get_catalogo_sucursales(:idEstatus)", nativeQuery = true)
    List<Map<String, Object>> getCatalogoSucursales(@Param("idEstatus") int idEstatus);
}
