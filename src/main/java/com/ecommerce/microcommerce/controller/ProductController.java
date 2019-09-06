package com.ecommerce.microcommerce.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    @Autowired //pour qu il instancie automatiquement notre couche de données (le repository)
    private ProductDao productDao;

    @GetMapping(value = "Produits")
    public List<Product> listeProduits(){
        return productDao.findAll();
    }

    @GetMapping(value="Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id){
        Product product=productDao.findById(id);
        return product;
    }
    @PostMapping(value = "/Produits")
    public ResponseEntity<Void> ajouterProduit(@RequestBody Product product){//les données se trouvent dans le corp de la requette RequestBody et il va essayer de le convertir en product
        Product product1= productDao.save(product);
        if(product==null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder //cette classe permet de creer un lien à partir d une requette
                        .fromCurrentRequest() //on va lui demander de creer à partir de la requette courante
                        .path("/{id}") //on va lui demander d ajouter à cet URI un id
                        .buildAndExpand(product1.getId()) //on remplace cet url par sont contenu
                        .toUri();//tous mettre en URI

        //La différence entre URL et URI est simple après avoir connu leurs définitions:
        //Identificateur de ressource uniforme (URI) - séquence de caractères permettant l'identification complète de toute ressource abstraite ou physique
        //Uniform Resource Locator (URL) - un sous-ensemble d'URI qui, en plus d'identifier le lieu où une ressource est disponible, décrit le mécanisme principal pour y accéder.

            return ResponseEntity.created(location).build();
    }
}
