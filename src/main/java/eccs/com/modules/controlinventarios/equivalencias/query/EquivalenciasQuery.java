package eccs.com.modules.controlinventarios.equivalencias.query;

import eccs.com.modules.controlinventarios.equivalencias.entity.EquivalenciasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface EquivalenciasQuery extends JpaRepository<EquivalenciasEntity, Long> {

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_catalogo_equivalencias()", nativeQuery = true)
    List<Map<String, Object>> getCatalogoEquivalencias();

}
