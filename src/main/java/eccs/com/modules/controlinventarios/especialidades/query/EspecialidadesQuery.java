package eccs.com.modules.controlinventarios.especialidades.query;

import eccs.com.modules.controlinventarios.especialidades.entity.EspecialidadesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EspecialidadesQuery extends JpaRepository<EspecialidadesEntity, Long> {

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_pizza_especialidad_ingredientes(:idEspecialidad, :idSucursal)", nativeQuery = true)
    List<Map<String, Object>> getIngredientesEspecialidad(@Param("idEspecialidad") Integer idEspecialidad, @Param("idSucursal") Integer idSucursal);

}
