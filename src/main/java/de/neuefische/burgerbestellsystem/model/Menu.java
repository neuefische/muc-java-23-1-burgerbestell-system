package de.neuefische.burgerbestellsystem.model;

public record Menu(
        String id,
        String name,
        double price,
        String mainDish,
        String sideDish,
        String beverage
) {
}
