package eccs.com.auth.login.query;

import eccs.com.auth.login.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AuthQuery extends JpaRepository<AuthEntity, Long> {

    @Query(value = "SELECT * FROM \"controlauth\".fn_login(:usuario, :pass)", nativeQuery = true)
    List<Map<String, Object>> findByLogin(@Param("usuario") String usuario, @Param("pass") String pass);
}
