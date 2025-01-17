package com.madhu.exception;

import lombok.AllArgsConstructor;

public class TransactionException extends Exception {
       public TransactionException(String msg){
		   super(msg);
	   }
}
