package com.platzi.market.domain.service;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//Cuando  creamos un servicio es necesario decorarlo como servicio
public class ProductService {
    @Autowired//para que spring sepa que debe usar, instancia la clase productoRepository
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    //Por ahora los productos escasos no se implementa

    public Product save(Product product){
        return  productRepository.save(product);
    }

    public boolean delete(int productId){
        return getProduct(productId).map(product -> {//El map se ejecuta en caso de que el producto exista
            productRepository.delete(productId);
            return true;
        }).orElse(false);//en caso de que no se encuentre el producto
    }
}
