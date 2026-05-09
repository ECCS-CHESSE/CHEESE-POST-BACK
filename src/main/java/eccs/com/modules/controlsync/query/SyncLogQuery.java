package eccs.com.modules.controlsync.query;

import eccs.com.modules.controlsync.entity.SyncLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SyncLogQuery extends JpaRepository<SyncLogEntity, Long> {

    @Query(value = "SELECT id, query_fallido, error_msg, fecha, resuelto, id_sucursal FROM eccs_sync_log ORDER BY fecha DESC", nativeQuery = true)
    List<Map<String, Object>> getLogs();

    @Query(value = "SELECT id, query_fallido, error_msg, fecha, resuelto, id_sucursal FROM eccs_sync_log WHERE resuelto = false ORDER BY fecha DESC", nativeQuery = true)
    List<Map<String, Object>> getLogsPendientes();

    @Query(value = "SELECT COUNT(*) > 0 AS pendiente, COUNT(*) AS total_errores FROM eccs_sync_log WHERE id_sucursal = :idSucursal AND resuelto = false", nativeQuery = true)
    Map<String, Object> getStatusSucursal(@Param("idSucursal") Integer idSucursal);
}
