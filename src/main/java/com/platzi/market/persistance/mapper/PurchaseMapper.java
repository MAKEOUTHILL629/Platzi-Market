package com.platzi.market.persistance.mapper;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.PurchaseItem;
import com.platzi.market.persistance.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItem.class})
public interface PurchaseMapper {

    @Mappings({
        @Mapping(source = "idCompra", target = "purchaseId"),
        @Mapping(source = "idCliente", target = "clienteId"),
        @Mapping(source = "fecha", target = "date"),
        @Mapping(source = "medioPago", target = "paymentMethod"),
        @Mapping(source = "estado", target = "state"),
        @Mapping(source = "comentario", target = "comment"),
        @Mapping(source = "productos", target = "items")
    })
    Purchase toPurchase(Compra compra);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra(Purchase purchase);

    List<Purchase> toPurchases(List<Compra> compras);
}
