package com.ProductSimulation.product.Service;

import com.ProductSimulation.product.Model.Product;
import com.ProductSimulation.product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> displayData(){
        return repository.findAll();
    }

    public Product saveProduct(Product p){
        return repository.save(p);
    }


    public void deleteProduct(int id){
        repository.deleteById(id);
    }
}
