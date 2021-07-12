package com.platzi.market.persistance.mapper;

import com.platzi.market.domain.Category;
import com.platzi.market.persistance.entity.Categoria;
import org.mapstruct.*;

@Mapper(componentModel = "spring")//Es un mapeador, por otro lado tiene soporte con spring
public interface CategoryMapper {
    //El siguiente codigo es para convertir categoria a category
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active"),

            })
    Category toCategory(Categoria categoria);//Parsear una Categoria a una Category

    @InheritInverseConfiguration//no hay que usar la notacion de mappins, solamente se hace el proceso inversamente
    @Mapping(target = "productos", ignore = true)//como se sabe que vamos a obviar el parametro productos, hacemos que el mapeo lo ignore
    Categoria toCategoria(Category category);
}
