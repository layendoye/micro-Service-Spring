package com.ecommerce.microcommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"prixAchat","id"})
public class Product {

    private int id;
    private String nom;
    private int prix;

    //@JsonIgnore //pour l'ignorer lors de l'affichage
    private int prixAchat;

    public Product(){} //créé un constructeur par defaut car jaxon qu on utilise pour parser du json en objet necessite un constructeur par defaut

    public Product(int id,String nom,int prix,int prixAchat){
        this.id=id;
        this.nom=nom;
        this.prix=prix;
        this.prixAchat=prixAchat;
    }
    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setPrixAchat(int prixAchat) {
        this.prix = prixAchat;
    }

    public int getPrixAchat() {
        return this.prixAchat;
    }



    @Override
    public String toString() {
        return super.toString();
//        return "Product{"+
//                "id="+id+
//                ", nom='"+nom+'\''+
//                ", prix="+prix+
//                "}"
//                ;
    }
}
