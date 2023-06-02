package de.neuefische.burgerbestellsystem.service;

import de.neuefische.burgerbestellsystem.exception.MenuNotFoundException;
import de.neuefische.burgerbestellsystem.model.Menu;
import de.neuefische.burgerbestellsystem.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getAllMenus() {
        return menuRepository.getAllMenus();
    }

    public Menu getMenuById(String id) {
        return menuRepository.getMenuById(id);
    }

    public Menu addMenu(Menu menu) {
        return menuRepository.addMenu(menu);
    }

    public Menu updateMenu(String id, Menu menu) {
        return menuRepository.updateMenu(id, menu);
    }

    public void deleteMenu(String id) {
        menuRepository.deleteMenu(id);
    }
}
