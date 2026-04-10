package eccs.com.modules.controldashboard.sidebar.query;

import eccs.com.modules.controldashboard.sidebar.entity.SidebarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SidebarQuery extends JpaRepository<SidebarEntity, Long> {

    @Query(value = "SELECT * FROM \"eccs\".app_menu(:id)", nativeQuery = true)
    List<Map<String, Object>> findMenuByIdEmpleado(@Param("id") Integer id);
}
    