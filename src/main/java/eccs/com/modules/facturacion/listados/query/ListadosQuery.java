package eccs.com.modules.facturacion.listados.query;

import eccs.com.modules.facturacion.listados.entity.ListadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ListadosQuery extends JpaRepository<ListadosEntity, Long> {

    @Query(value = "SELECT id, codigo, codigo || ' - ' || descripcion AS descripcion FROM sat_regimenfiscalcfdi", nativeQuery = true)
    List<Map<String, Object>> getRegimenesFiscales();

    @Query(value = "SELECT id, codigo, codigo || ' - ' || descripcion AS descripcion FROM sat_usocfdi WHERE receptores ILIKE CONCAT('%', :codigo, '%')", nativeQuery = true)
    List<Map<String, Object>> getUsosCfdi(@Param("codigo") String codigo);
}
