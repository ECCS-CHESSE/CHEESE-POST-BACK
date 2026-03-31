package eccs.com.auth.login.query;

import eccs.com.auth.login.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AuthQuery extends JpaRepository<AuthEntity, Long> {

    @Query(value = "SELECT * FROM users WHERE user = :user AND pass = :pass", nativeQuery = true)
    Map<String, Object> findByUserAndPass(@Param("user") String user, @Param("pass") String pass);
}
