package com.lakesidehotel.LakeSideHotel.exeption;

public class UserAlreadyExistsExeption extends RuntimeException {
    public UserAlreadyExistsExeption(String message) {
        super(message);
    }
}
