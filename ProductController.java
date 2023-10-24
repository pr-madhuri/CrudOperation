package com.ProductSimulation.product.Controller;

import com.ProductSimulation.product.Model.Product;
import com.ProductSimulation.product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String getMessage(){
        return "index";
    }

    @GetMapping("/displayProduct")
    public String displayProductToAdmin(Model model){
        model.addAttribute("displayProduct",service.displayData());
        return "adminDisplay";
    }

    @GetMapping("/noAccess")
    public String noAccess(){
        return "access";
    }

    @GetMapping("/displayUserProduct")
    public String displayProductToUser(Model model){
        model.addAttribute("displayProduct",service.displayData());
        return "user";
    }

    @GetMapping("/purchaseTempProduct/{id}")
    public String getTempProductObject(@PathVariable int id, Model model){
         List<Product> list=service.displayData();
         for(Product p:list){
             if(p.getProductId()==id){
                 model.addAttribute("tempProduct",p);
                 break;
             }
         }

         return "purchaseProduct";
    }

    @GetMapping("/purchaseProduct")
    public String purchaseProduct(Product product){
        boolean status=false;
        List<Product> list=service.displayData();
        for(Product temp:list){
            if(temp.getProductId()== product.getProductId()){
                if(product.getUserQty()<=temp.getProductQty() && product.getUserQty()>0){
                    Product p=new Product(temp.getProductId(),temp.getProductName(),temp.getProductPerUnitPrice(),(temp.getProductQty()-product.getUserQty()), product.getUserQty());
                    service.saveProduct(p);
                    status=true;
                    break;
                }
            }
        }
        if(status){
            return "redirect:/displayUserProduct";
        }
        else{
             return "invalidProductQty";
        }

    }
    @GetMapping("/updateCurrentProduct/{id}")
    public String updateProduct(Model model, @PathVariable int id){
        List<Product> list=service.displayData();
        Product temp;
         for(Product p:list){
             if(p.getProductId()==id){
                 temp=p;
                 model.addAttribute("tempProduct",temp);
                 break;
             }
         }
           return "updateProuct";
    }

    @GetMapping("/updateProduct")
    public String update(Product p){
          service.saveProduct(p);
          return "redirect:/displayProduct";
    }

    @GetMapping("/deleteCurrentUser/{id}")
    public String delete(@PathVariable int id){
        service.deleteProduct(id);
        return "redirect:/displayProduct";
    }

    @GetMapping("/addProduct")
     public String addProduct(Model model){
        model.addAttribute("product",new Product());
        return "addProduct";
     }

     @PostMapping("/addProductInfo")
     public String add(Product p){
        service.saveProduct(p);
         return "redirect:/displayProduct";
     }
}
