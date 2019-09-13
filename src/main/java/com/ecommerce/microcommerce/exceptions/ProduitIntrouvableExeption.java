package com.ecommerce.microcommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProduitIntrouvableExeption extends RuntimeException {//modifier l extends en RuntimeException
    public ProduitIntrouvableExeption(String s) {
        super(s); //passer le message à la classe parent
    }
}
