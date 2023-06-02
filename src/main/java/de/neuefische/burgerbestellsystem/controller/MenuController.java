package de.neuefische.burgerbestellsystem.controller;

import de.neuefische.burgerbestellsystem.model.Menu;
import de.neuefische.burgerbestellsystem.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Menu getMenuById(@PathVariable String id) {
        return menuService.getMenuById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Menu addMenu(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Menu updateMenu(@PathVariable String id, @RequestBody Menu menu) {
        return menuService.updateMenu(id, menu);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMenu(@PathVariable String id) {
        menuService.deleteMenu(id);
    }
}
