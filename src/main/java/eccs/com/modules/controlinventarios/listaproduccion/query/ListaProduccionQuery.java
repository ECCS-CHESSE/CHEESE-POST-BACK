package eccs.com.modules.controlinventarios.listaproduccion.query;

import eccs.com.modules.controlinventarios.listaproduccion.entity.ListaProduccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ListaProduccionQuery extends JpaRepository<ListaProduccionEntity, Long> {

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_lst_tipo_salsa(:id)", nativeQuery = true)
    List<Map<String, Object>> getLstTipoSalsa(@Param("id") Integer id);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_lst_orillas_queso(:id)", nativeQuery = true)
    List<Map<String, Object>> getLstOrillasQueso(@Param("id") Integer id);

}
