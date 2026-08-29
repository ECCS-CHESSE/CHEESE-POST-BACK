package eccs.com.modules.controlinventarios.listaproduccion.query;

import eccs.com.modules.controlinventarios.listaproduccion.entity.ListaProduccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ListaProduccionQuery extends JpaRepository<ListaProduccionEntity, Long> {

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_lst_tipo_salsa(:id, :id_equivalencia)", nativeQuery = true)
    List<Map<String, Object>> getLstTipoSalsa(@Param("id") Integer id, @Param("id_equivalencia") Integer id_equivalencia);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_lst_orillas_queso(:id, :id_equivalencia )", nativeQuery = true)
    List<Map<String, Object>> getLstOrillasQueso(@Param("id") Integer id, @Param("id_equivalencia") Integer id_equivalencia);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_lst_insumos(:id, :id_equivalencia)", nativeQuery = true)
    List<Map<String, Object>> getLstInsumos(@Param("id") Integer id, @Param("id_equivalencia") Integer id_equivalencia);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_lst_especialidades()", nativeQuery = true)
    List<Map<String, Object>> getLstEspecialidades();

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_data_ingredientes_data(:idSucursal, :idEspecialidad)", nativeQuery = true)
    List<Map<String, Object>> getDataIngredientes(@Param("idSucursal") Integer idSucursal, @Param("idEspecialidad") Integer idEspecialidad);

    @Query(value = "SELECT * FROM \"controlinventarios\".fn_get_lst_categorias_arma_tu_pizza()", nativeQuery = true)
    List<Map<String, Object>> getLstCategoriasArmaTuPizza();

}
