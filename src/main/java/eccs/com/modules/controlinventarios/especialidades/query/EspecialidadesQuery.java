package eccs.com.modules.controlinventarios.especialidades.query;

import eccs.com.modules.controlinventarios.especialidades.entity.EspecialidadesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadesQuery extends JpaRepository<EspecialidadesEntity, Long> {
}
