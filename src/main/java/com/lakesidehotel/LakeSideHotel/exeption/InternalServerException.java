package com.lakesidehotel.LakeSideHotel.exeption;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
