package eccs.com.modules.controlventas.catalogoclientes.query;

import eccs.com.modules.controlventas.catalogoclientes.dto.CatalogoClientesRequestDto;
import eccs.com.modules.controlventas.catalogoclientes.entity.CatalogoClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CatalogoClientesQuery extends JpaRepository<CatalogoClientesEntity, Long> {

    @Query(value = "SELECT * FROM \"controlventas\".fn_get_catalogo_clientes(:#{#request.id_sucursal}, :#{#request.id_estatus})", nativeQuery = true)
    List<Map<String, Object>> getCatalogoClientes(@Param("request") CatalogoClientesRequestDto request);
}
