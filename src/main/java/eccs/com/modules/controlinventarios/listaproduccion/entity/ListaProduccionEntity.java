package eccs.com.modules.controlinventarios.listaproduccion.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "eccs_lista_produccion")
@Data
public class ListaProduccionEntity {
    @Id
    private Long id;
}
