package com.lakesidehotel.LakeSideHotel.exeption;

public class RoleAlreadyExistException extends RuntimeException {
    public RoleAlreadyExistException(String message) {
        super(message);
    }
}
