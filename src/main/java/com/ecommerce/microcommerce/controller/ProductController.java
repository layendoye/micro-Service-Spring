package com.ecommerce.microcommerce.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.exceptions.ProduitIntrouvableExeption;
import com.ecommerce.microcommerce.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(description = "Gestion des produits")
@RestController
public class ProductController {//07:00

    @Autowired //pour qu il instancie automatiquement notre couche de données (le repository)
    private ProductDao productDao;

    @GetMapping(value = "Produits")
    public List<Product> listeProduits(){
        return productDao.findAll();
    }

    @ApiOperation(value = "Récupère un produit selon son id")
    @GetMapping(value="Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) throws ProduitIntrouvableExeption {
        Product product=productDao.findById(id);
        if(product==null)
            throw new ProduitIntrouvableExeption("Le produit avec l'id "+id+" n'existe pas");
        return product;
    }
    @PostMapping(value = "/Produits")// @Valid pour lui dire que le produit doit etre valide
    public ResponseEntity<Void> ajouterProduit(@Valid @RequestBody Product product){//les données se trouvent dans le corp de la requette RequestBody et il va essayer de le convertir en product
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
    @GetMapping(value="test/Produits/{prixLimit}")
    public List<Product> test(@PathVariable int prixLimit){

        return productDao.findByPrixGreaterThan(prixLimit);
        //return productDao.chercherUnProdruitCher(prixLimit);
    }
}
