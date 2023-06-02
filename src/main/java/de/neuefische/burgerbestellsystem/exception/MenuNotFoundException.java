package de.neuefische.burgerbestellsystem.exception;

public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException(String id) {
        super("Menu with id " + id + " not found!");
    }
}
