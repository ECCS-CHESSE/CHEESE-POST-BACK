package eccs.com.modules.controlvalidaciones.empleadonip.query;

import eccs.com.modules.controlvalidaciones.empleadonip.entity.EmpleadoNipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmpleadoNipQuery extends JpaRepository<EmpleadoNipEntity, Long> {

    @Query(value = "SELECT * FROM \"controlauth\".fn_validar_nip(:idEmpleado, :nip)", nativeQuery = true)
    List<Map<String, Object>> validarNip(@Param("idEmpleado") Integer idEmpleado, @Param("nip") String nip);
}
