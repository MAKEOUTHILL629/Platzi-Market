package com.platzi.market.persistance;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistance.crud.ProductoCrudRepository;
import com.platzi.market.persistance.entity.Producto;
import com.platzi.market.persistance.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository//Porque esta interactuando con la base de datos, porque es desde aqui donde se estan haciendo las operaciones @Component es una notacion mas general
public class ProductoRepository implements ProductRepository {

    //Objeto que manipula la tabla producto
    @Autowired//ceder el control a spring para crear esas instancias, debe ser un componente de spring
    private ProductoCrudRepository productoCrudRepository;
    //Mappeador de Producto a Product
    @Autowired//ceder el control a spring para crear esas instancias
    private ProductMapper mapper;


    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) this.productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        //Realizando la consulta de productos por categoria
        List<Producto> productos = (List<Producto>) this.productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        //se retorna un Optional de la lista de products por eso se hace el parseo
        return  Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos =  this.productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);

        return productos.map(product -> mapper.toProducts(product));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        //Se hace el casteo de un Optional<Producto> a Optiona<Product>, con el mapper
        return  this.productoCrudRepository.findById(productId).map(produto -> mapper.toProduct(produto));
    }

    @Override
    public Product save(Product product) {
        return mapper.toProduct(productoCrudRepository.save(mapper.toProducto(product)));
    }


//Comentariados porque se configuro la clase a responder con Product
//    public Optional<List<Producto>> getEscasos(int cantidad){
//        return this.productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
//    }

//    public Optional<Producto> getProducto(int idProducto){
//        //Consultando un producto por id
//        return this.productoCrudRepository.findById(idProducto);
//    }

//    public Producto save(Producto producto){
//        //Guardando un producto
//        return  this.productoCrudRepository.save(producto);
//    }

    @Override
    public void delete(int idProducto){
        //Borrando dada la clave primaria
        this.productoCrudRepository.deleteById(idProducto);
    }
}
