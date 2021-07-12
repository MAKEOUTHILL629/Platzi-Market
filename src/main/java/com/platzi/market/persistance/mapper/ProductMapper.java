package com.platzi.market.persistance.mapper;

import com.platzi.market.domain.Product;
import com.platzi.market.persistance.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})//Como es necesario convertir categoria a category, y ya se encuentra mappeado, solo se le especifica
public interface ProductMapper {
    //Mapeando de Producto a Product
    @Mappings({
            @Mapping( source = "idProducto", target = "productId" ),
            @Mapping( source = "nombre", target = "name" ),
            @Mapping( source = "idCategoria", target = "categoryId" ),
            @Mapping( source = "precioVenta", target = "price" ),
            @Mapping( source = "cantidadStock", target = "stock" ),
            @Mapping( source = "estado", target = "active" ),
            @Mapping( source = "categoria", target = "category" ),
    })
    Product toProduct(Producto producto);

    //Como ya hicimos la conversion arriba utiliza esa conversion para el siguiente metodo
    List<Product> toProducts(List<Producto> productos);

    @InheritInverseConfiguration//Tome el mapeo pero de manera inversa
    @Mapping(target = "codigoBarras", ignore = true)//cuando sea codigo de barras lo ignore y no los incluya a product, porque no es necesario
    Producto toProducto(Product product);
}
