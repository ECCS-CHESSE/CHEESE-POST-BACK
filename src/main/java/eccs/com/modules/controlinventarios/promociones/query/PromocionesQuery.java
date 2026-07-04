package eccs.com.modules.controlinventarios.promociones.query;

import eccs.com.modules.controlinventarios.promociones.entity.PromocionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionesQuery extends JpaRepository<PromocionesEntity, Long> {
}
