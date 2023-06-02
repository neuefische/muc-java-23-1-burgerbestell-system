package de.neuefische.burgerbestellsystem.repository;

import de.neuefische.burgerbestellsystem.exception.MenuNotFoundException;
import de.neuefische.burgerbestellsystem.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Repository
public class MenuRepository {

    private final List<Menu> menus;

    public MenuRepository(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Menu> getAllMenus() {
        return menus;
    }

    public Menu getMenuById(String id) {
        for (Menu menu : menus) {
            if (menu.id().equals(id)) {
                return menu;
            }
        }
        throw new MenuNotFoundException("Menu with id " + id + "not found");
    }

    public Menu addMenu(Menu menu) {
        Menu menuToAdd = new Menu(
                menu.id(),
                menu.name(),
                menu.price(),
                menu.mainDish(),
                menu.sideDish(),
                menu.beverage());

        menus.add(menuToAdd);

        return menuToAdd;
    }

    public Menu updateMenu(String id, Menu menu) {
        Menu menuToDelete = getMenuById(id);

        Menu menuToUpdate = new Menu(
                id,
                menu.name(),
                menu.price(),
                menu.mainDish(),
                menu.sideDish(),
                menu.beverage()
        );

        menus.remove(menuToDelete);
        menus.add(menuToUpdate);

        return menuToUpdate;
    }

    public void deleteMenu(String id) {
        Menu menuToDelete = getMenuById(id);
        menus.remove(menuToDelete);
    }

}
