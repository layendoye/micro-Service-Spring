package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {//en premier parametre l objet en 2em le type de son id (Integer)
//pour voir les methode native aller sur : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

    Product findById(int id);
    List<Product> findByPrixGreaterThan(int prixLimit);
    //@Query("SELECT id, nom, prix From Product p WHERE p.prix > :prixLimit")
    //List<Product> chercherUnProdruitCher(@Param("prixLimit") int prix);//il va lier prixLimit et le prix recu en parametre
}//12:00
