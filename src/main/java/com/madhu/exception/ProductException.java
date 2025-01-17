package com.madhu.exception;

import lombok.AllArgsConstructor;

public class ProductException extends Exception {

    public ProductException(String msg){
        super(msg);
    }
}
