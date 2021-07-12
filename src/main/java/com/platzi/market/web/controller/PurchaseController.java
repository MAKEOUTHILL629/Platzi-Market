package com.platzi.market.web.controller;


import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService service;


    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{idCliente}")
    public ResponseEntity<List<Purchase>> getByIdCliente(@PathVariable("idCliente") String clientId){
        return service.getByClient(clientId).filter(Predicate.not(List::isEmpty))
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        return  new ResponseEntity<>(service.save(purchase), HttpStatus.CREATED);
    }
}
