package eccs.com.modules.controlventas.tickets.query;

import eccs.com.modules.controlventas.tickets.entity.TicketsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TicketsQuery extends JpaRepository<TicketsEntity, Long> {

    @Query(value = "SELECT * FROM \"controlventas\".fn_get_catalogo_ventas(:id_sucursal)", nativeQuery = true)
    List<Map<String, Object>> getTickets(@Param("id_sucursal") Integer id_sucursal);
}
