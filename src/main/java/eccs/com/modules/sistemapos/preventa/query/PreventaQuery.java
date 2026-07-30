package eccs.com.modules.sistemapos.preventa.query;

import eccs.com.modules.sistemapos.preventa.entity.PreventaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PreventaQuery extends JpaRepository<PreventaEntity, Long> {

    @Query(value = "SELECT * FROM \"sistemapos\".fn_get_precios_pizzas(:idSucursal)", nativeQuery = true)
    List<Map<String, Object>> getPreciosPizzas(@Param("idSucursal") Integer idSucursal);
}
