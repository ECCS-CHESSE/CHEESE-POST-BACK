package eccs.com.modules.controlinventarios.especialidades.query;

import eccs.com.modules.controlinventarios.especialidades.entity.PizzaEspecialidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaEspecialidadQuery extends JpaRepository<PizzaEspecialidadEntity, Long> {
}
