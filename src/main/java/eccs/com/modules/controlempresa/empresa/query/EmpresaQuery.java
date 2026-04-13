package eccs.com.modules.controlempresa.empresa.query;

import eccs.com.modules.controlempresa.empresa.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmpresaQuery extends JpaRepository<EmpresaEntity, Long> {

    @Query(value = "SELECT * FROM \"controlempresa\".fn_get_data_empresa()", nativeQuery = true)
    List<Map<String, Object>> getDataEmpresa();
}
