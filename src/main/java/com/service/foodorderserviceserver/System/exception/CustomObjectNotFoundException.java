package com.service.foodorderserviceserver.System.exception;

public class CustomObjectNotFoundException extends RuntimeException{

    public CustomObjectNotFoundException(String objectName, String id) {
        super("Could not find " + objectName + " with Id " + id + " :(");
    }

    public CustomObjectNotFoundException(String objectName, Integer id) {
        super("Could not find " + objectName + " with Id " + id + " :(");
    }

}
