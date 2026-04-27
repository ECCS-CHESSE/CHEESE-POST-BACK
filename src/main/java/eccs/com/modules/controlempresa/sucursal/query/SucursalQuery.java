package eccs.com.modules.controlempresa.sucursal.query;

import eccs.com.modules.controlempresa.sucursal.entity.SucursalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SucursalQuery extends JpaRepository<SucursalEntity, Long> {

    @Query(value = "SELECT * FROM \"controlempresa\".fn_create_sucursal(:sucursal)", nativeQuery = true)
    List<Map<String, Object>> createSucursal(@Param("sucursal") String sucursal);
}
