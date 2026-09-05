package com.amazonclone.exception;

import org.springframework.http.HttpStatus;

public class InsufficientStockException extends BaseException {

    public InsufficientStockException(String productName, int requested, int available) {
        super(
                "Insufficient stock for '%s'. Requested: %d, available: %d"
                        .formatted(productName, requested, available),
                HttpStatus.BAD_REQUEST,
                "INSUFFICIENT_STOCK"
        );
    }
}
