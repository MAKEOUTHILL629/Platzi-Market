package com.platzi.market.persistance.crud;

import com.platzi.market.persistance.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {//La clase que va a ser mapeada y el tipo de dato de la llave primary

    //@Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true) se puede hacer de esta manera, y se le puede cambiar el nombre al metodo
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);//Un query metodo que retorna una lista de productos a partir de una categoria

    //Se realiza la consulta de los productos que tienen en stock una cantidad menor y esten activos para la venta
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}
