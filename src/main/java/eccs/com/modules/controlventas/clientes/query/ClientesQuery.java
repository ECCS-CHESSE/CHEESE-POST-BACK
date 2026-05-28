package eccs.com.modules.controlventas.clientes.query;

import eccs.com.modules.controlventas.catalogoclientes.dto.CatalogoClientesRequestDto;
import eccs.com.modules.controlventas.catalogoclientes.entity.CatalogoClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClientesQuery extends JpaRepository<CatalogoClientesEntity, Long> {

    //@Query(value = "SELECT * FROM \"controlventas\".fn_get_catalogo_clientes(:#{#request.id_sucursal}, :#{#request.id_estatus})", nativeQuery = true)
    //List<Map<String, Object>> getCatalogoClientes(@Param("request") CatalogoClientesRequestDto request);

    @Query(value = "SELECT * FROM \"controlventas\".fn_create_eccs_cliente(:id_eccs_sucursal, :id_eccs_cliente, :nombre, :apellidoPaterno, :apellidoMaterno, :telefono)", nativeQuery = true)
    List<Map<String, Object>> getCrearCliente(
        @Param("id_eccs_sucursal") Integer id_eccs_sucursal,
        @Param("id_eccs_empleado") Integer id_eccs_empleado,
        @Param("nombre")  String nombre,
        @Param("apellidoPaterno")  String apellidoPaterno,
        @Param("apellidoMaterno")  String apellidoMaterno,
        @Param("telefono")  String telefono
    );
    

}
