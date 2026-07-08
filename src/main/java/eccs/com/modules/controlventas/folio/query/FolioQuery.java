package eccs.com.modules.controlventas.folio.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import eccs.com.modules.controlventas.folio.entity.FolioEntity;

import java.util.List;
import java.util.Map;

@Repository
public interface FolioQuery extends JpaRepository<FolioEntity, Long> {

    @Query(value = "SELECT * FROM \"controlventas\".fn_buscar_folio(:folio)", nativeQuery = true)
    List<Map<String, Object>> buscarFolio(@Param("folio") Integer folio);
}
