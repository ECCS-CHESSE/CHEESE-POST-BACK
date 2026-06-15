package eccs.com.modules.controlventas.directoriosucursales.query;

import eccs.com.modules.controlventas.directoriosucursales.entity.DirectorioSucursalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DirectorioSucursalesQuery extends JpaRepository<DirectorioSucursalesEntity, Long> {

    @Query(value = "SELECT * FROM \"controlventas\".fn_get_directorio_sucursales(:id_sucursal)", nativeQuery = true)
    List<Map<String, Object>> getDirectorioSucursales(@Param("id_sucursal") Integer id_sucursal);
}
