package com.platzi.market.domain.repository;

import com.platzi.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();//Obtener todas las compras
    Optional<List<Purchase>> getByClient(String clientId);//obtener la compra de un cliente
    Purchase save(Purchase purchase);


}
