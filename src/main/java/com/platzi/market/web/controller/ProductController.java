package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController//El controlador de la api rest, es decir le informa a spring que es una api de tipo rest
@RequestMapping("/products")//En que Path se van a aceptar la peticiones que se le haran
public class ProductController {
    @Autowired//como es un componente de spring, se le deja la tarea a spring de instanciar el objeto
    private ProductService productService;

    @GetMapping("/all")//GetMapping porque estamos obteniendo informacion, por otro lado responde al path all
    @ApiOperation("Get All SuperMarket Products")
    @ApiResponse(code = 200, message = "Todo correcto")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);//se le envia el body o el resultado, y el httpStatus que dice que fue llamada de manera correcta
    }

    @GetMapping("/{id}")//como el parametro viaja por la url, se indica la variable con los corchetes
    @ApiOperation("Search A Product With An ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Okay"),
            @ApiResponse(code = 404, message = "Product Not Found")

    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The Id Of The Product", required = true, example = "8") @PathVariable("id") int productId){//indispensable que usamos la variable del path
        //Como productService devuelve un Optional, se realiza el mapeo del response entity, en caso de que el objeto no exista se devuelve la peticion como not found
        return productService.getProduct(productId).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")//diferenciar del id, mejoramos el path para caetgory
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId).map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){//como el objeto debe viajar como un json, se indica que es por parte de la peticion del cuerpo
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")//se le indica que viene por parte del path
    public ResponseEntity delete(@PathVariable("id") int productId){
        if(productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
