package com.amazonclone.exception;

import org.springframework.http.HttpStatus;

public class InactiveProductException extends BaseException {

    public InactiveProductException(String productName) {
        super(
                "Product '%s' is not available".formatted(productName),
                HttpStatus.BAD_REQUEST,
                "INACTIVE_PRODUCT"
        );
    }
}
